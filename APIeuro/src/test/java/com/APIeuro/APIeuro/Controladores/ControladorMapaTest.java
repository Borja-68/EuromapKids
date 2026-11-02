package com.APIeuro.APIeuro.Controladores;

import com.APIeuro.APIeuro.DTO.CreaMapaDTO;
import com.APIeuro.APIeuro.DTO.TipoMapaDTO;
import com.APIeuro.APIeuro.Entidades.Mapa;
import com.APIeuro.APIeuro.Servicios.ServicioMapa;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControladorMapaTest {
    private final ServicioMapa servicio = mock(ServicioMapa.class);
    private final ControladorMapa controlador = new ControladorMapa(servicio);

    @Test void crearMapaGuardaLosDatosYRespondeOk() {
        byte[] imagen = {1, 2};
        var respuesta = controlador.crearMapa(new CreaMapaDTO("Europa", imagen));
        ArgumentCaptor<Mapa> captor = ArgumentCaptor.forClass(Mapa.class);
        verify(servicio).guardar(captor.capture());
        assertEquals("Europa", captor.getValue().getTipoMapa());
        assertArrayEquals(imagen, captor.getValue().getImagen());
        assertEquals(200, respuesta.getStatusCode().value());
    }

    @Test void borrarMapaDevuelveConflictoSiNoExiste() {
        when(servicio.idExiste(4)).thenReturn(false);
        assertEquals(409, controlador.borrarMapa(4).getStatusCode().value());
        verify(servicio, never()).remove(anyInt());
    }

    @Test void borrarMapaEliminaSiExiste() {
        when(servicio.idExiste(4)).thenReturn(true);
        assertEquals(200, controlador.borrarMapa(4).getStatusCode().value());
        verify(servicio).remove(4);
    }

    @Test void modificarMapaRechazaIdInexistente() {
        when(servicio.idExiste(4)).thenReturn(false);
        assertEquals(409, controlador.modificarMapa(4, new CreaMapaDTO("Nuevo", null)).getStatusCode().value());
        verify(servicio, never()).modificar(any());
    }

    @Test void modificarMapaActualizaSoloCamposRecibidos() {
        Mapa mapa = new Mapa(4, "Viejo", new byte[]{1}, List.of(), List.of());
        when(servicio.idExiste(4)).thenReturn(true);
        when(servicio.obtenerMapaPorId(4)).thenReturn(mapa);
        var respuesta = controlador.modificarMapa(4, new CreaMapaDTO("Nuevo", null));
        assertEquals(200, respuesta.getStatusCode().value());
        assertEquals("Nuevo", mapa.getTipoMapa());
        assertArrayEquals(new byte[]{1}, mapa.getImagen());
        verify(servicio).modificar(mapa);
    }

    @Test void obtenerMapasDevuelveElResultadoDelServicio() {
        List<TipoMapaDTO> mapas = List.of(new TipoMapaDTO(4, new byte[]{1}));
        when(servicio.obtenerMapasPorTipo("Europa")).thenReturn(mapas);
        var respuesta = controlador.obtenerMapasTipo("Europa");
        assertEquals(409, respuesta.getStatusCode().value());
        assertSame(mapas, respuesta.getBody());
    }
}
