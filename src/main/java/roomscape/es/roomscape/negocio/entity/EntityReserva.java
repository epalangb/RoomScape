package roomscape.es.roomscape.negocio.entity;

import lombok.Data;
import java.util.Calendar;
import roomscape.es.roomscape.negocio.reserva.TReserva;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
public class EntityReserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean activo;
    private int participantes;
    private String nombreEscapeRoom;
    private Calendar fechaIni;
    private Calendar fechaFin;

    public EntityReserva(){

    }

    public EntityReserva(TReserva tReserva){
        this.participantes = tReserva.getParticipantes();
        this.nombreEscapeRoom = tReserva.getNombreEscapeRoom();
        this.fechaIni = tReserva.getFechaIni();
        this.fechaFin = tReserva.getFechaFin();
        this.activo = tReserva.isActivo();
    }

    public TReserva toTransfer(){
        TReserva tReserva = new TReserva();
        tReserva.setId(this.id);
        tReserva.setActivo(this.activo);
        tReserva.setParticipantes(this.participantes);
        tReserva.setNombreEscapeRoom(this.nombreEscapeRoom);
        tReserva.setFechaIni(this.fechaIni);
        tReserva.setFechaFin(this.fechaFin);

        return tReserva;
    }
}