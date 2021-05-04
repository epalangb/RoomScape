package roomscape.es.roomscapefrontend.models;

import lombok.Data;

@Data
public class TEscapeRoom {
    private int id;
    private boolean activo;
    private int capacidadPersonas;
    private int duracion;
    private String nombre;
    private double precio;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("ID: " + id + System.lineSeparator());
        sb.append("Nombre: " + nombre + System.lineSeparator());
        sb.append("Capacidad de personas: " + capacidadPersonas + System.lineSeparator());
        sb.append("Duración: " + duracion + System.lineSeparator());
        sb.append("Precio: " + precio + System.lineSeparator());
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
