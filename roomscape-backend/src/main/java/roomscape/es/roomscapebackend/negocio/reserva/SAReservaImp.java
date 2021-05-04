package roomscape.es.roomscapebackend.negocio.reserva;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomscape.es.roomscapebackend.negocio.entity.EntityEscapeRoom;
import roomscape.es.roomscapebackend.negocio.entity.EntityReserva;
import roomscape.es.roomscapebackend.negocio.exceptions.validations.*;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryEscapeRoom;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryReserva;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class SAReservaImp implements SAReserva {

    private static final Logger log = LoggerFactory.getLogger(SAReservaImp.class);

    @Autowired
    private RepositoryReserva repositoryReserva;
    @Autowired
    private RepositoryEscapeRoom repositoryEscapeRoom;

    public TReserva crearReserva(TReserva tReserva) throws Exception {

        Optional<EntityEscapeRoom> auxEscapeRoomOpt = repositoryEscapeRoom.findEntityEscapeRoomByNombre(tReserva.getNombreEscapeRoom());
        if (!auxEscapeRoomOpt.isPresent()) { //Miramos si existe escape room a reservar
            throw new InvalidEscapeRoomException();
        }

        EntityEscapeRoom auxEscapeRoom = auxEscapeRoomOpt.get();

        int duration = auxEscapeRoom.getDuracion(); //Miramos duracion de escape room elegida

        EntityReserva auxReserva = repositoryReserva.findEntityReservaByNombreEscapeRoomAndFechaIni(tReserva.getNombreEscapeRoom(), tReserva.getFechaIni().getTime());
        Optional<EntityReserva> optional = Optional.ofNullable(auxReserva);

        Date dateIni = tReserva.getFechaIni().getTime();
        Calendar fechaFin = new Calendar.Builder().setInstant(dateIni).build();

        fechaFin.add(Calendar.MINUTE, duration);
        tReserva.setFechaFin(fechaFin); //añadimos la duracion del escape room a la reserva

        ArrayList<EntityReserva> escapeRoomList = repositoryReserva.findEntityReservaByNombreEscapeRoom(tReserva.getNombreEscapeRoom());

        Boolean ocupado = false;
        for (EntityReserva entityReserva : escapeRoomList) { //Comprobamos si se sobreponen horarios
            if (tReserva.getFechaIni().getTime().before(entityReserva.getFechaFin()) && tReserva.getFechaFin().getTime().after(entityReserva.getFechaIni())) {
                ocupado = true;

            } //(StartA <= EndB) and (EndA >= StartB)  https://stackoverflow.com/questions/325933/determine-whether-two-date-ranges-overlap
        }

        ValidationException e = null;

        if (optional.isPresent() && optional.get().isActivo()) { //Ya existe reserva a escape room a esa hora
            e = new InvalidReservationException();
        } else if (ocupado) { //El rango elegido esta ocupado
            e = new InvalidReservationOverlapsException();
        } else if (auxEscapeRoom.getCapacidadPersonas() < tReserva.getParticipantes()) { //Mayor numero de participantes que capacidad de escape rooom
            e = new InvalidParticipantException();
        } else if (tReserva.getParticipantes() <= 0) {
            e = new InvalidNumberOfParticipantsException();
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
            entityReserva.setFechaIni(tReserva.getFechaIni().getTime());
            entityReserva.setParticipantes(tReserva.getParticipantes());
            entityReserva.setNombreEscapeRoom(tReserva.getNombreEscapeRoom());
            entityReserva.setFechaFin(tReserva.getFechaFin().getTime());
        } else {
            tReserva.setActivo(true);
            entityReserva = new EntityReserva(tReserva);
        }
        log.debug("La reserva ha superado las reglas de validación");

        EntityReserva entityReservaSaved = repositoryReserva.save(entityReserva);

        return entityReservaSaved.toTransfer();
    }
}