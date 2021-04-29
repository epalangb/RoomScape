package roomscape.es.roomscape.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import roomscape.es.roomscape.negocio.entity.EntityReserva;

import java.util.ArrayList;
import java.util.Calendar;

public interface RepositoryReserva extends JpaRepository<EntityReserva, Void>, JpaSpecificationExecutor<EntityReserva>  {
    EntityReserva findEntityReservaByEscapeRoomAndDate(String nombreEscapeRoom, Calendar fecha);
    ArrayList<EntityReserva> findEntityReservaByEscapeRoom(String nombreEscapeRoom);
}