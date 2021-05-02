package roomscape.es.roomscapebackend.negocio.entity;

import lombok.Data;
import roomscape.es.roomscapebackend.negocio.escape_room.TEscapeRoom;

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
        this.activo = tEscapeRoom.isActivo();
        this.capacidadPersonas = tEscapeRoom.getCapacidadPersonas();
        this.duracion = tEscapeRoom.getDuracion();
        this.nombre = tEscapeRoom.getNombre();
        this.precio = tEscapeRoom.getPrecio();
    }

    public TEscapeRoom toTransfer() {

        TEscapeRoom tEscapeRoom = new TEscapeRoom();
        tEscapeRoom.setActivo(this.activo);
        tEscapeRoom.setCapacidadPersonas(this.capacidadPersonas);
        tEscapeRoom.setDuracion(this.duracion);
        tEscapeRoom.setId(this.id);
        tEscapeRoom.setNombre(this.nombre);
        tEscapeRoom.setPrecio(this.precio);

        return tEscapeRoom;
    }
}
