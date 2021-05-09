package roomscape.es.roomscapebackend.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import roomscape.es.roomscapebackend.negocio.entity.EntityReserva;

import java.util.ArrayList;
import java.util.Date;

public interface RepositoryReserva extends JpaRepository<EntityReserva, Integer>, JpaSpecificationExecutor<EntityReserva> {

    EntityReserva findEntityReservaByNombreEscapeRoomAndFechaIni(String nombreEscapeRoom, Date fecha);

    ArrayList<EntityReserva> findEntityReservaByNombreEscapeRoom(String nombreEscapeRoom);
    /*
    @Query("SELECT reserva FROM Reserva WHERE reserva.nombreEscapeRoom = ?1 AND ?2 <= reserva.fechaFin AND ?3  >= reserva.fechaIni")
    EntityReserva findOverlappingReservations(String nombreEscapeRoom, Date fechaIni, Date fechaFin);
    */
}
