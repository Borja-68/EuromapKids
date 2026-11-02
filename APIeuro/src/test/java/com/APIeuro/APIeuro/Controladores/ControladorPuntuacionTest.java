package com.APIeuro.APIeuro.Controladores;

import com.APIeuro.APIeuro.DTO.*;
import com.APIeuro.APIeuro.Entidades.*;
import com.APIeuro.APIeuro.Servicios.ServicioMapa;
import com.APIeuro.APIeuro.Servicios.ServicioPuntuacion;
import com.APIeuro.APIeuro.Servicios.ServicioUsuario;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControladorPuntuacionTest {
    private final ServicioPuntuacion puntuaciones = mock(ServicioPuntuacion.class);
    private final ServicioUsuario usuarios = mock(ServicioUsuario.class);
    private final ServicioMapa mapas = mock(ServicioMapa.class);
    private final ControladorPuntuacion controlador = new ControladorPuntuacion(puntuaciones, usuarios, mapas);

    private Usuario usuario() { return new Usuario(7, "ana", "ana@example.com", "hash", "es", null, false, List.of()); }
    private Mapa mapa() { return new Mapa(5, "Europa", null, List.of(), List.of()); }

    @Test void crearPuntuacionRechazaUsuarioInexistente() {
        when(usuarios.idExiste(7)).thenReturn(false);
        assertEquals(409, controlador.crearPuntuacion(new CreaPuntuacionDTO(5, 7, 6, 4, 30)).getStatusCode().value());
        verifyNoInteractions(mapas);
        verify(puntuaciones, never()).guardar(any());
    }

    @Test void crearPuntuacionRechazaMapaInexistente() {
        when(usuarios.idExiste(7)).thenReturn(true);
        when(mapas.idExiste(5)).thenReturn(false);
        assertEquals(409, controlador.crearPuntuacion(new CreaPuntuacionDTO(5, 7, 6, 4, 30)).getStatusCode().value());
        verify(puntuaciones, never()).guardar(any());
    }

    @Test void crearPuntuacionGuardaPuntosRangoYRelaciones() {
        Usuario usuario = usuario();
        Mapa mapa = mapa();
        when(usuarios.idExiste(7)).thenReturn(true);
        when(mapas.idExiste(5)).thenReturn(true);
        when(usuarios.obtenerUsuarioPorId(7)).thenReturn(usuario);
        when(mapas.obtenerMapaPorId(5)).thenReturn(mapa);
        when(puntuaciones.calcularPuntuacion(6, 4)).thenReturn(600);
        when(puntuaciones.obtenerRango(600)).thenReturn('B');

        assertEquals(200, controlador.crearPuntuacion(new CreaPuntuacionDTO(5, 7, 6, 4, 30)).getStatusCode().value());
        var captor = org.mockito.ArgumentCaptor.forClass(Puntuacion.class);
        verify(puntuaciones).guardar(captor.capture());
        assertEquals(600, captor.getValue().getPuntuacion());
        assertEquals('B', captor.getValue().getRango());
        assertEquals(new PuntuacionPK(5, 7), captor.getValue().getId());
        assertSame(usuario, captor.getValue().getUsuarioPuntuacion());
        assertSame(mapa, captor.getValue().getMapaPuntuacion());
    }

    @Test void borrarPuntuacionRechazaSiNoExiste() {
        when(puntuaciones.puntuacionExiste(5, 7)).thenReturn(false);
        assertEquals(409, controlador.borrarPuntuacion(5, 7).getStatusCode().value());
        verify(puntuaciones, never()).remove(any());
    }

    @Test void borrarPuntuacionEliminaPorClaveCompuesta() {
        when(puntuaciones.puntuacionExiste(5, 7)).thenReturn(true);
        assertEquals(200, controlador.borrarPuntuacion(5, 7).getStatusCode().value());
        verify(puntuaciones).remove(new PuntuacionPK(5, 7));
    }

    @Test void modificarPuntuacionRechazaRegistroInexistente() {
        when(puntuaciones.puntuacionExiste(5, 7)).thenReturn(false);
        assertEquals(409, controlador.modificarPuntuacion(new CreaPuntuacionDTO(5, 7, 6, 4, 30)).getStatusCode().value());
        verify(puntuaciones, never()).modificar(any());
    }

    @Test void modificarPuntuacionAceptaMejorPuntuacionYMejorTiempo() {
        Puntuacion anterior = new Puntuacion(5, 5, 40, 500, 'C');
        when(puntuaciones.puntuacionExiste(5, 7)).thenReturn(true);
        when(puntuaciones.obtenerPuntuacion(5, 7)).thenReturn(anterior);
        when(puntuaciones.calcularPuntuacion(7, 3)).thenReturn(700);
        when(puntuaciones.obtenerRango(700)).thenReturn('B');
        assertEquals(200, controlador.modificarPuntuacion(new CreaPuntuacionDTO(5, 7, 7, 3, 20)).getStatusCode().value());
        assertEquals(700, anterior.getPuntuacion());
        assertEquals('B', anterior.getRango());
        assertEquals(20, anterior.getTiempo());
        verify(puntuaciones).modificar(anterior);
    }

    @Test void modificarPuntuacionAceptaMismaPuntuacionConMejorTiempo() {
        Puntuacion anterior = new Puntuacion(6, 4, 40, 600, 'B');
        when(puntuaciones.puntuacionExiste(5, 7)).thenReturn(true);
        when(puntuaciones.obtenerPuntuacion(5, 7)).thenReturn(anterior);
        when(puntuaciones.calcularPuntuacion(6, 4)).thenReturn(600);
        assertEquals(200, controlador.modificarPuntuacion(new CreaPuntuacionDTO(5, 7, 6, 4, 20)).getStatusCode().value());
        assertEquals(20, anterior.getTiempo());
        verify(puntuaciones).modificar(anterior);
    }

    @Test void modificarPuntuacionConservaLaMejorAnterior() {
        Puntuacion anterior = new Puntuacion(6, 4, 20, 600, 'B');
        when(puntuaciones.puntuacionExiste(5, 7)).thenReturn(true);
        when(puntuaciones.obtenerPuntuacion(5, 7)).thenReturn(anterior);
        when(puntuaciones.calcularPuntuacion(5, 5)).thenReturn(500);
        assertEquals(409, controlador.modificarPuntuacion(new CreaPuntuacionDTO(5, 7, 5, 5, 30)).getStatusCode().value());
        verify(puntuaciones, never()).modificar(any());
    }

    @Test void obtenerClasificacionDevuelveElServicio() {
        List<DatosClasificacionDTO> datos = List.of(new DatosClasificacionDTO("ana", 600, 20));
        when(puntuaciones.obtenerClasificacion(5)).thenReturn(datos);
        var respuesta = controlador.obtenerClasificacion(5);
        assertEquals(409, respuesta.getStatusCode().value());
        assertSame(datos, respuesta.getBody());
    }

    @Test void obtenerPuntuacionPersonalDevuelveElServicio() {
        PuntuacionPersonalDTO datos = new PuntuacionPersonalDTO(6, 4, 20, 600, 'B');
        when(puntuaciones.obtenerPuntuacionPersonal(5, 7)).thenReturn(datos);
        var respuesta = controlador.datosUsuario(5, 7);
        assertEquals(409, respuesta.getStatusCode().value());
        assertSame(datos, respuesta.getBody());
    }
}
