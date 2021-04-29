package roomscape.es.roomscape.negocio.reserva;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import roomscape.es.roomscape.negocio.entity.EntityEscapeRoom;
import roomscape.es.roomscape.negocio.entity.EntityReserva;
import roomscape.es.roomscape.negocio.exceptions.validations.*;
import roomscape.es.roomscape.negocio.repository.RepositoryEscapeRoom;
import roomscape.es.roomscape.negocio.repository.RepositoryReserva;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;


public class SAReservaImp implements  SAReserva {

    private static final Logger log = LoggerFactory.getLogger(SAReservaImp.class);

    @Autowired
    private RepositoryReserva repositoryReserva;
    @Autowired
    private RepositoryEscapeRoom repositoryEscapeRoom;

    @Transactional
    public TReserva crearReserva(TReserva tReserva) throws Exception {

        EntityEscapeRoom auxEscapeRoom = repositoryEscapeRoom.findEntityEscapeRoomByNombre(tReserva.getNombreEscapeRoom());
        ValidationException e = null;
        if (auxEscapeRoom == null) { //Miramos si existe escape room a reservar
            e = new InvalidEscapeRoomException();
            throw e;
        }
        int duration = auxEscapeRoom.getDuracion(); //Miramos duracion de escape room elegida

        EntityReserva auxReserva = repositoryReserva.findEntityReservaByEscapeRoomAndDate(tReserva.getNombreEscapeRoom(), tReserva.getFechaIni());
        Optional<EntityReserva> optional = Optional.ofNullable(auxReserva);

        Calendar fechaFin = tReserva.getFechaIni();
        fechaFin.add(Calendar.MINUTE, duration);
        tReserva.setFechaFin(fechaFin); //añadimos la duracion del escape room a la reserva

        ArrayList<EntityReserva> escapeRoomList = repositoryReserva.findEntityReservaByEscapeRoom(tReserva.getNombreEscapeRoom());

        Boolean ocupado = false;
        for(EntityReserva entityReserva: escapeRoomList){ //Comprobamos si se sobreponen horarios
            if(tReserva.getFechaIni().before(entityReserva.getFechaFin()) && tReserva.getFechaFin().after(entityReserva.getFechaIni())){
                ocupado = true;
            } //(StartA <= EndB) and (EndA >= StartB)  https://stackoverflow.com/questions/325933/determine-whether-two-date-ranges-overlap
        }

        if (optional.isPresent() && optional.get().isActivo()) { //Ya existe reserva a escape room a esa hora
            e = new InvalidReservationException();
        } else if (ocupado){ //El rango elegido esta ocupado
            e = new InvalidReservationOverlapsException();
        }
        else if (auxEscapeRoom.getCapacidadPersonas() < tReserva.getParticipantes()) { //Mayor numero de participantes que capacidad de escape rooom
            e = new InvalidParticipantException();
        }

        if (Optional.ofNullable(e).isPresent()) {
            log.warn("La reserva no ha superado las reglas de validación: {}",
                    e.getMessage());
            throw e;
        }

        EntityReserva entityReserva;

        if (optional.isPresent()) {
            entityReserva = optional.get();
            entityReserva.setActivo(true);
            entityReserva.setFechaIni(tReserva.getFechaIni());
            entityReserva.setParticipantes(tReserva.getParticipantes());
            entityReserva.setNombreEscapeRoom(tReserva.getNombreEscapeRoom());
            entityReserva.setFechaFin(tReserva.getFechaFin());
        } else {
            tReserva.setActivo(true);
            entityReserva = new EntityReserva(tReserva);
        }
        log.debug("La reserva ha superado las reglas de validación");

        EntityReserva entityReservaSaved = repositoryReserva.save(entityReserva);

        return entityReservaSaved.toTransfer();
    }
}