package roomscape.es.roomscapebackend.negocio.login;

import roomscape.es.roomscapebackend.negocio.client.TClient;

public interface SALogin {
    TClient login (TLogin loginData) throws Exception;
}
