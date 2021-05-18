package roomscape.es.roomscapebackend.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import roomscape.es.roomscapebackend.negocio.entity.EntityClient;

import java.util.Optional;


public interface RepositoryClient extends JpaRepository<EntityClient, Integer>, JpaSpecificationExecutor<EntityClient> {

    Optional<EntityClient> findEntityClientByDni (String dni);
    Optional<EntityClient> findEntityClientByUser (String user);
}
