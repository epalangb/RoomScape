package roomscape.es.roomscape.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import roomscape.es.roomscape.negocio.entity.EntityReserva;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public interface RepositoryReserva extends JpaRepository<EntityReserva, Integer>, JpaSpecificationExecutor<EntityReserva> {

    EntityReserva findEntityReservaByNombreEscapeRoomAndFechaIni(String nombreEscapeRoom, Date fecha);

    ArrayList<EntityReserva> findEntityReservaByNombreEscapeRoom(String nombreEscapeRoom);
}