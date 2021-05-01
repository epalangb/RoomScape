package es.roomscape.roomscapefrontend.presentacion.controller;

import es.roomscape.roomscapefrontend.presentacion.Event;

public class Context {

    private Object data;
    private Event event;

    public Context(Object data, Event event) {
        this.data = data;
        this.event = event;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object datos) {
        this.data = datos;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
