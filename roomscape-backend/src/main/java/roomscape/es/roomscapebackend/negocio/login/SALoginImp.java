package roomscape.es.roomscapebackend.negocio.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomscape.es.roomscapebackend.negocio.client.TClient;
import roomscape.es.roomscapebackend.negocio.entity.EntityClient;
import roomscape.es.roomscapebackend.negocio.exceptions.validations.InvalidPasswordException;
import roomscape.es.roomscapebackend.negocio.exceptions.validations.InvalidUserNotExistsException;
import roomscape.es.roomscapebackend.negocio.exceptions.validations.ValidationException;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryClient;

import java.util.Optional;

@Service
@Transactional
public class SALoginImp implements SALogin{
    @Autowired
    private RepositoryClient repositoryClient;
    private static final Logger log = LoggerFactory.getLogger(SALogin.class);

    @Override
    public TClient login(TLogin loginData) throws Exception {
        Optional<EntityClient> auxClient = repositoryClient.findEntityClientByUser(loginData.getUser());
        ValidationException e = null;
        if(!auxClient.isPresent()){
            e = new InvalidUserNotExistsException();
        } else if (!auxClient.get().getPassword().equals(loginData.getPassword())){
            e = new InvalidPasswordException();
        }
        if (Optional.ofNullable(e).isPresent()) {
            log.warn("El login del cliente ha fallado: {}",
                    e.getMessage());
            throw e;
        }
        return auxClient.get().toTransfer();
    }
}
