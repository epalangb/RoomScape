package roomscape.es.roomscapebackend.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import roomscape.es.roomscapebackend.negocio.entity.EntityEscapeRoom;
import roomscape.es.roomscapebackend.negocio.entity.EntityReserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RepositoryReserva extends JpaRepository<EntityReserva, Integer>, JpaSpecificationExecutor<EntityReserva> {

    EntityReserva findEntityReservaByNombreEscapeRoomAndFechaIni(String nombreEscapeRoom, Date fecha);

    ArrayList<EntityReserva> findEntityReservaByNombreEscapeRoom(String nombreEscapeRoom);

    @Query("SELECT er FROM EntityReserva er WHERE er.escapeRoom=:escapeRoom AND er.fechaFin  >= :endDate")
    Optional<List<EntityReserva>> findReservationsByEscapeRoomAfterDate(EntityEscapeRoom escapeRoom, Date endDate);
}
