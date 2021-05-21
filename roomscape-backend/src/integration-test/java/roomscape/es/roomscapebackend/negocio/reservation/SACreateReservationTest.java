package roomscape.es.roomscapebackend.negocio.reservation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import roomscape.es.roomscapebackend.negocio.entity.EntityReserva;
import roomscape.es.roomscapebackend.negocio.repository.RepositoryReserva;

import java.util.Calendar;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SACreateReservationTest {

        @Autowired
        SAReserva saReserva;
        @Autowired
        RepositoryReserva repositoryReservation;

        @BeforeEach
        void init() {
            repositoryReservation.deleteAll();
        }

        @Test
        @DisplayName("Comprobación de que una reserva se da de alta correctamente")
        public void altaEscapeRoomExito() {

            EntityReserva entityReserva = new EntityReserva();
            entityReserva.setNombreEscapeRoom("Test EscapeRoom");
            entityReserva.setParticipantes(10);
            entityReserva.setActivo(true);
            Calendar calIni = Calendar.getInstance();
            calIni.set(2021,1,1,20,20,20);
            entityReserva.setFechaIni(calIni.getTime());
            Calendar calFin = Calendar.getInstance();
            calIni.set(2021,10,10,20,20,20);
            entityReserva.setFechaFin(calFin.getTime());

            repositoryReservation.save(entityReserva);
            try {
                List<EntityReserva> reservationSaved = repositoryReservation.findAll();
                Assertions.assertEquals(reservationSaved.get(0).getParticipantes(), 10);
                Assertions.assertEquals(reservationSaved.get(0).getNombreEscapeRoom(), "Test EscapeRoom");
                Assertions.assertEquals(reservationSaved.get(0).getId(), 1);
                Assertions.assertEquals(reservationSaved.get(0).getFechaIni().compareTo(calIni.getTime()), -1);
                Assertions.assertEquals(reservationSaved.get(0).getFechaFin().compareTo(calIni.getTime()), -1);
                Assertions.assertTrue(reservationSaved.get(0).isActivo());

            } catch (Exception e) {
                Assertions.assertNull(e);
            }
        }
    }
