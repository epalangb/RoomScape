package roomscape.es.roomscape.Presentación.Controller;

import roomscape.es.roomscape.Presentación.Eventos;

public class Context {

    private Object datos;
    private Eventos evento;

    public Context(Object datos, Eventos evento) {
        this.datos=datos;
        this.evento=evento;
    }
    public Object getDatos() {
        return this.datos;
    }
    public Eventos getEvento() {
        return this.evento;
    }
}
