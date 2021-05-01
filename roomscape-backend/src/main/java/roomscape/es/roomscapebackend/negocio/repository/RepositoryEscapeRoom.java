package roomscape.es.roomscapebackend.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import roomscape.es.roomscapebackend.negocio.entity.EntityEscapeRoom;

public interface RepositoryEscapeRoom extends JpaRepository<EntityEscapeRoom, Void>, JpaSpecificationExecutor<EntityEscapeRoom> {
    EntityEscapeRoom findEntityEscapeRoomByNombre(String nombre);
}
