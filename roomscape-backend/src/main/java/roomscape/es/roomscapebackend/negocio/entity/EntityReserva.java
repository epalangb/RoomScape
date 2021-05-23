package roomscape.es.roomscapebackend.negocio.entity;

import lombok.Data;
import roomscape.es.roomscapebackend.negocio.reservation.TReserva;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class EntityReserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean activo;
    private int participantes;
    private String cliente;
    private double precio;
    private int duracion;
    private String nombreEscapeRoom;
    private Date fechaIni;
    private Date fechaFin;
    @OneToOne
    private EntityEscapeRoom escapeRoom;

    public EntityReserva() {

    }

    public EntityReserva(TReserva tReserva, EntityEscapeRoom escapeRoom) {
        this.participantes = tReserva.getParticipantes();
        this.nombreEscapeRoom = tReserva.getNombreEscapeRoom();
        this.fechaIni = tReserva.getFechaIniInDateFormat().getTime();
        this.fechaFin = tReserva.getFechaFinInDateFormat().getTime();
        this.duracion = tReserva.getDuracion();
        this.activo = tReserva.isActivo();
        this.precio = tReserva.getPrecio();
        this.escapeRoom = escapeRoom;
        this.cliente = tReserva.getCliente();
    }

    public TReserva toTransfer() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        TReserva tReserva = new TReserva();
        tReserva.setId(this.id);
        tReserva.setActivo(this.activo);
        tReserva.setParticipantes(this.participantes);
        tReserva.setPrecio(this.precio);
        tReserva.setDuracion(this.duracion);
        tReserva.setNombreEscapeRoom(this.nombreEscapeRoom);
        tReserva.setFechaIni(sdf.format(this.fechaIni.getTime()));
        tReserva.setFechaFin(sdf.format(this.fechaFin.getTime()));

        return tReserva;
    }
}