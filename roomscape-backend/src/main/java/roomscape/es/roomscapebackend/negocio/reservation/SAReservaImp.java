package roomscape.es.roomscapebackend.negocio.reservation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomscape.es.roomscapebackend.negocio.entity.EntityEscapeRoom;
import roomscape.es.roomscapebackend.negocio.entity.EntityReserva;
import roomscape.es.roomscapebackend.negocio.exceptions.list.NoReservationsAvailableException;
import roomscape.es.roomscapebackend.negocio.exceptions.validations.*;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryEscapeRoom;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryReserva;

import java.text.SimpleDateFormat;
import java.util.*;

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

        EntityReserva auxReserva = repositoryReserva.findEntityReservaByNombreEscapeRoomAndFechaIni(tReserva.getNombreEscapeRoom(), tReserva.getFechaIniInDateFormat().getTime());
        Optional<EntityReserva> optional = Optional.ofNullable(auxReserva);

        Date dateIni = tReserva.getFechaIniInDateFormat().getTime();
        Calendar fechaFin = new Calendar.Builder().setInstant(dateIni).build();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        fechaFin.add(Calendar.MINUTE, duration);
        tReserva.setFechaFin(sdf.format(fechaFin.getTime())); //añadimos la duracion del escape room a la reserva

        ArrayList<EntityReserva> escapeRoomList = repositoryReserva.findEntityReservaByNombreEscapeRoom(tReserva.getNombreEscapeRoom());

        Boolean ocupado = false;
        for (EntityReserva entityReserva : escapeRoomList) { //Comprobamos si se sobreponen horarios
            if (tReserva.getFechaIniInDateFormat().getTime().before(entityReserva.getFechaFin()) && tReserva.getFechaFinInDateFormat().getTime().after(entityReserva.getFechaIni())) {
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
            entityReserva.setFechaIni(tReserva.getFechaFinInDateFormat().getTime());
            entityReserva.setParticipantes(tReserva.getParticipantes());
            entityReserva.setNombreEscapeRoom(tReserva.getNombreEscapeRoom());
            entityReserva.setFechaFin(tReserva.getFechaFinInDateFormat().getTime());
        } else {
            tReserva.setActivo(true);
            entityReserva = new EntityReserva(tReserva);
        }
        log.debug("La reserva ha superado las reglas de validación");

        EntityReserva entityReservaSaved = repositoryReserva.save(entityReserva);

        return entityReservaSaved.toTransfer();
    }

    @Override
    public List<TReserva> listByDateAndHour(Calendar reservationDate) throws Exception {
        List<TReserva> tReservations = new ArrayList<>();
        List<EntityReserva> reservations = repositoryReserva.findAll();
        reservations.stream()
                .filter(reservation -> reservation.getFechaIni().compareTo(reservationDate.getTime()) >= 0)
                .forEach(reservation -> tReservations.add(reservation.toTransfer()));
        if (tReservations.isEmpty())
            throw new NoReservationsAvailableException();
        Collections.sort(tReservations);
        return tReservations;
    }
}