package com.APIeuro.APIeuro.Servicios;

import com.APIeuro.APIeuro.Repositorios.PuntuacionRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ServicioPuntuacionTest {
    private final ServicioPuntuacion servicio = new ServicioPuntuacion(mock(PuntuacionRepository.class));

    @Test void calculaPuntuacionSobreMil() { assertEquals(600, servicio.calcularPuntuacion(6, 4)); }
    @Test void puntuacionCeroSiNoHayAciertos() { assertEquals(0, servicio.calcularPuntuacion(0, 5)); }
    @Test void asignaRangos() {
        assertEquals('F', servicio.obtenerRango(200));
        assertEquals('D', servicio.obtenerRango(300));
        assertEquals('C', servicio.obtenerRango(500));
        assertEquals('B', servicio.obtenerRango(600));
        assertEquals('A', servicio.obtenerRango(750));
        assertEquals('S', servicio.obtenerRango(900));
    }

    @Test void formatoTiempoConvierteMilisegundosATexto() {
        assertEquals("2.0:5.0:0", servicio.formatoTiempo(125000));
    }

    @Test void obtenerTotalDevuelveAciertosSobreRespuestasTotales() {
        assertEquals("6/10", servicio.obtenerTotal(6, 4));
    }
}
