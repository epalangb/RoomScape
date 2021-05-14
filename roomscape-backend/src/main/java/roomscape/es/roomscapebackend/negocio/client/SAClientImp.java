package roomscape.es.roomscapebackend.negocio.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomscape.es.roomscapebackend.negocio.entity.EntityClient;
import roomscape.es.roomscapebackend.negocio.exceptions.validations.*;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryClient;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class SAClientImp implements SAClient {

    private static final Logger log = LoggerFactory.getLogger(SAClientImp.class);

    @Autowired
    private RepositoryClient repositoryClient;

    @Override
    public TClient createClient(TClient tClient) throws Exception {
        Optional<EntityClient> auxClient = repositoryClient.findEntityClientByDni(tClient.getDni());
        Optional<EntityClient> auxClient2 = repositoryClient.findEntityClientByUser(tClient.getUser());

        ValidationException e = null;
        if (auxClient.isPresent() && auxClient.get().isActive()) {
            e = new InvalidDniExistsException();
        } else if (auxClient2.isPresent() && auxClient2.get().isActive()) {
            e = new InvalidNameException();
        } else if (validateDni(tClient.getDni())) {
            e = new InvalidDniException();
        } else if (validateUser(tClient.getUser())){
            e = new InvalidUserFormatException();
        }
        if (Optional.ofNullable(e).isPresent()) {
            log.warn("La creacion del cliente no ha superado las reglas de validación: {}",
                    e.getMessage());
            throw e;
        }

        EntityClient entityClient;

        if (auxClient.isPresent()) {
            entityClient = auxClient.get();
            entityClient.setActive(true);
            entityClient.setDni(tClient.getDni());
            entityClient.setPassword(tClient.getPassword());
            entityClient.setUser(tClient.getUser());
        } else {
            tClient.setActive(true);
            entityClient = new EntityClient(tClient);
        }

        log.debug("El cliente: {} ha superado las reglas de validación", tClient.getUser());

        EntityClient entityClientSaved = repositoryClient.save(entityClient);

        return entityClientSaved.toTransfer();
    }

    public boolean validateDni(String dni) {
        if (dni == null)
            return false;
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        if (dni.length() != 9)
            return false;
        if (!Pattern.matches("^\\d{1,8}[" + letras + "]$", dni))
            return false;
        int num;
        try {
            num = Integer.parseInt(dni.substring(0, 8));
        } catch (NumberFormatException nfe) {
            return false;
        }
        return dni.charAt(8) == letras.charAt(num % 23);
    }

    public boolean validateUser(String user) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9á-üÁ-Ü-_. ]");
        Matcher matcher = pattern.matcher(user);
        if(matcher.find()) {
            return true;
        }
        return false;
    }
}