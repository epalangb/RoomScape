package roomscape.es.roomscapebackend.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import roomscape.es.roomscapebackend.negocio.entity.EntityEscapeRoom;

import java.util.Optional;

public interface RepositoryEscapeRoom extends JpaRepository<EntityEscapeRoom, Integer>, JpaSpecificationExecutor<EntityEscapeRoom> {

    Optional<EntityEscapeRoom> findEntityEscapeRoomByNombre(String nombre);
}
