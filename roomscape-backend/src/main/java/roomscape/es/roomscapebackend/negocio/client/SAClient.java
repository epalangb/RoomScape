package roomscape.es.roomscapebackend.negocio.client;

public interface SAClient {
    TClient createClient(TClient tClient) throws Exception;
    boolean clientLogin (Object loginData) throws Exception;
}
