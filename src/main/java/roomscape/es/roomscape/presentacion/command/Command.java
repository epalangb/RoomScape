package roomscape.es.roomscape.presentacion.command;

import roomscape.es.roomscape.presentacion.controller.Context;

public abstract class Command {

    //Devuelve un Contexto con el Transfer que recibe el SA y el evento a realizar.
    public abstract Context execute(Object datos);

}
