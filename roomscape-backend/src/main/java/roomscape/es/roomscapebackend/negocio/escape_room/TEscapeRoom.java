package roomscape.es.roomscapebackend.negocio.escape_room;

import lombok.Data;

@Data
public class TEscapeRoom {
    private int id;
    private boolean activo;
    private int capacidadPersonas;
    private int duracion;
    private String nombre;
    private double precio;
}
