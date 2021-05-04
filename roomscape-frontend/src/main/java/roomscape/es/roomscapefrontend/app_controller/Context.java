package roomscape.es.roomscapefrontend.app_controller;

import lombok.Data;

@Data
public class Context {

    private Object data;
    private Event event;

    public Context(Object data, Event event) {
        this.data = data;
        this.event = event;
    }

    public Context() {
    }
}
