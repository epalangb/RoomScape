package es.roomscape.roomscapefrontend.presentacion.command;

import es.roomscape.roomscapefrontend.presentacion.controller.Context;

public abstract class Command {

    // Devuelve un Contexto con el Transfer que recibe el SA y el evento a realizar.
    public abstract Context execute(Object data);

}
