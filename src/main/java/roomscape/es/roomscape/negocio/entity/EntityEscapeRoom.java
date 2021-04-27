package roomscape.es.roomscape.negocio.entity;

import lombok.Data;
import roomscape.es.roomscape.negocio.escape_room.TEscapeRoom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class EntityEscapeRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean activo;
    private int capacidadPersonas;
    private int duracion;
    private String nombre;
    private double precio;

    public EntityEscapeRoom() {
    }

    public EntityEscapeRoom(TEscapeRoom tEscapeRoom) {
        this.nombre = tEscapeRoom.getNombre();
        this.capacidadPersonas = tEscapeRoom.getCapacidadPersonas();
        this.duracion = tEscapeRoom.getDuracion();
        this.precio = tEscapeRoom.getPrecio();
        this.activo = tEscapeRoom.isActivo();
    }

    public TEscapeRoom toTransfer() {

        TEscapeRoom tEscapeRoom = new TEscapeRoom();
        tEscapeRoom.setActivo(this.activo);
        tEscapeRoom.setId(this.id);
        tEscapeRoom.setActivo(this.activo);
        tEscapeRoom.setCapacidadPersonas(this.capacidadPersonas);
        tEscapeRoom.setDuracion(this.duracion);
        tEscapeRoom.setNombre(this.nombre);
        tEscapeRoom.setPrecio(this.precio);

        return tEscapeRoom;
    }
}
