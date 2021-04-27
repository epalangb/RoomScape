package roomscape.es.roomscape.Presentación.Command;

import roomscape.es.roomscape.Presentación.Controller.Context;

public abstract class Command {

    //Devuelve un Contexto con el Transfer que recibe el SA y el evento a realizar.
    public abstract Context execute(Object datos);

}
