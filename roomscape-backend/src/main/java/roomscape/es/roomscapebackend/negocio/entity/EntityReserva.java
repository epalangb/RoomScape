package roomscape.es.roomscapebackend.negocio.entity;

import lombok.Data;
import roomscape.es.roomscapebackend.negocio.reservation.TReserva;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;


@Entity
@Data
public class EntityReserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean activo;
    private int participantes;
    private String nombreEscapeRoom;
    private Date fechaIni;
    private Date fechaFin;

    public EntityReserva() {

    }

    public EntityReserva(TReserva tReserva) {
        this.participantes = tReserva.getParticipantes();
        this.nombreEscapeRoom = tReserva.getNombreEscapeRoom();
        this.fechaIni = tReserva.getFechaIni().getTime();
        this.fechaFin = tReserva.getFechaFin().getTime();
        this.activo = tReserva.isActivo();
    }

    public TReserva toTransfer() {
        TReserva tReserva = new TReserva();
        tReserva.setId(this.id);
        tReserva.setActivo(this.activo);
        tReserva.setParticipantes(this.participantes);
        tReserva.setNombreEscapeRoom(this.nombreEscapeRoom);
        tReserva.setFechaIni(new Calendar.Builder().setInstant(this.fechaIni).build());
        tReserva.setFechaFin(new Calendar.Builder().setInstant(this.fechaFin).build());

        return tReserva;
    }
}