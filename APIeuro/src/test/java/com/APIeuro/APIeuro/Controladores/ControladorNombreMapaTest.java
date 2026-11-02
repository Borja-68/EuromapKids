package com.APIeuro.APIeuro.Controladores;

import com.APIeuro.APIeuro.DTO.CreaNombreMapaDTO;
import com.APIeuro.APIeuro.Entidades.Mapa;
import com.APIeuro.APIeuro.Entidades.NombreMapa;
import com.APIeuro.APIeuro.Entidades.NombreMapaPK;
import com.APIeuro.APIeuro.Servicios.ServicioMapa;
import com.APIeuro.APIeuro.Servicios.ServicioMapaNombre;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControladorNombreMapaTest {
    private final ServicioMapa mapas = mock(ServicioMapa.class);
    private final ServicioMapaNombre nombres = mock(ServicioMapaNombre.class);
    private final ControladorNombreMapa controlador = new ControladorNombreMapa(mapas, nombres);

    @Test void crearNombreRechazaMapaInexistente() {
        when(mapas.idExiste(5)).thenReturn(false);
        assertEquals(409, controlador.crearNombreMapa(new CreaNombreMapaDTO(5, "Spain", "en")).getStatusCode().value());
        verify(nombres, never()).guardar(any());
    }

    @Test void crearNombreRechazaIdiomaDuplicado() {
        when(mapas.idExiste(5)).thenReturn(true);
        when(nombres.idMapaExiste(5, "en")).thenReturn(true);
        assertEquals(409, controlador.crearNombreMapa(new CreaNombreMapaDTO(5, "Spain", "en")).getStatusCode().value());
        verify(nombres, never()).guardar(any());
    }

    @Test void crearNombreGuardaNombreConMapaEIdioma() {
        Mapa mapa = new Mapa(5, "Europa", null, java.util.List.of(), java.util.List.of());
        when(mapas.idExiste(5)).thenReturn(true);
        when(nombres.idMapaExiste(5, "en")).thenReturn(false);
        when(mapas.obtenerMapaPorId(5)).thenReturn(mapa);
        var respuesta = controlador.crearNombreMapa(new CreaNombreMapaDTO(5, "Spain", "en"));
        assertEquals(200, respuesta.getStatusCode().value());
        var captor = org.mockito.ArgumentCaptor.forClass(NombreMapa.class);
        verify(nombres).guardar(captor.capture());
        assertEquals("Spain", captor.getValue().getNombreMapa());
        assertSame(mapa, captor.getValue().getMapa());
        assertEquals(new NombreMapaPK(5, "en"), captor.getValue().getId());
    }

    @Test void borrarNombreRechazaNombreInexistente() {
        when(nombres.idMapaExiste(5, "en")).thenReturn(false);
        assertEquals(409, controlador.borrarNombreMapa(5, "en").getStatusCode().value());
        verify(nombres, never()).remove(any());
    }

    @Test void borrarNombreEliminaPorClaveCompuesta() {
        when(nombres.idMapaExiste(5, "en")).thenReturn(true);
        assertEquals(200, controlador.borrarNombreMapa(5, "en").getStatusCode().value());
        verify(nombres).remove(new NombreMapaPK(5, "en"));
    }

    @Test void modificarNombreRechazaClaveInexistente() {
        when(nombres.idMapaExiste(5, "en")).thenReturn(false);
        assertEquals(409, controlador.modificarNombreMapa(new CreaNombreMapaDTO(5, "Spain", "en")).getStatusCode().value());
        verify(nombres, never()).modificar(any());
    }

    @Test void modificarNombreActualizaElRegistro() {
        NombreMapa nombre = new NombreMapa("Old name");
        when(nombres.idMapaExiste(5, "en")).thenReturn(true);
        when(nombres.obtenerNombreMapa(5, "en")).thenReturn(nombre);
        assertEquals(200, controlador.modificarNombreMapa(new CreaNombreMapaDTO(5, "Spain", "en")).getStatusCode().value());
        assertEquals("Spain", nombre.getNombreMapa());
        verify(nombres).modificar(nombre);
    }

    @Test void obtenerNombreDevuelveConflictoSiNoExiste() {
        when(nombres.idMapaExiste(5, "en")).thenReturn(false);
        assertEquals(409, controlador.obtenerMapasTipo(5, "en").getStatusCode().value());
        verify(nombres, never()).obtenerNombreDelMapa(anyInt(), anyString());
    }

    @Test void obtenerNombreDevuelveElNombreRegistrado() {
        when(nombres.idMapaExiste(5, "en")).thenReturn(true);
        when(nombres.obtenerNombreDelMapa(5, "en")).thenReturn("Spain");
        var respuesta = controlador.obtenerMapasTipo(5, "en");
        assertEquals(409, respuesta.getStatusCode().value());
        assertEquals("Spain", respuesta.getBody());
    }
}
