package roomscape.es.roomscapefrontend.app_controller.command;

import roomscape.es.roomscapefrontend.app_controller.Context;

public abstract class Command {

    // Devuelve un Contexto con el Transfer que recibe el SA y el evento a realizar.
    public abstract Context execute(Object data);

}
