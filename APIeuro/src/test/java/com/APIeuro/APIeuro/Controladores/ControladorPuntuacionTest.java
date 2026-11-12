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

    @Test void obtenerClasificacionDevuelveElServicioConTiempoFormateado() {
        List<DatosClasificacionDTO> datos = List.of(new DatosClasificacionDTO("ana", 600, 125000));
        when(mapas.idExiste(5)).thenReturn(true);
        when(puntuaciones.obtenerClasificacion(5)).thenReturn(datos);
        when(puntuaciones.formatoTiempo(125000)).thenReturn("2.0:5.0:0");
        var respuesta = controlador.obtenerClasificacion(5);
        assertEquals(200, respuesta.getStatusCode().value());
        assertEquals(1, respuesta.getBody().size());
        assertEquals("ana", respuesta.getBody().get(0).getNombreUsuario());
        assertEquals(600, respuesta.getBody().get(0).getPuntuacion());
        assertEquals("2.0:5.0:0", respuesta.getBody().get(0).getTiempo());
    }

    @Test void obtenerClasificacionRechazaMapaInexistente() {
        when(mapas.idExiste(5)).thenReturn(false);
        var respuesta = controlador.obtenerClasificacion(5);
        assertEquals(409, respuesta.getStatusCode().value());
        assertTrue(respuesta.getBody().isEmpty());
        verify(puntuaciones, never()).obtenerClasificacion(5);
    }

    @Test void obtenerPuntuacionPersonalDevuelveResultadoYTiempoFormateados() {
        PuntuacionPersonalDTO datos = new PuntuacionPersonalDTO(6, 4, 90061, 600, 'B');
        when(puntuaciones.puntuacionExiste(5, 7)).thenReturn(true);
        when(puntuaciones.obtenerPuntuacionPersonal(5, 7)).thenReturn(datos);
        when(puntuaciones.obtenerTotal(6, 4)).thenReturn("6/10");
        when(puntuaciones.formatoTiempo(90061)).thenReturn("1.0:30.0:61");
        var respuesta = controlador.datosMapaUsuario(5, 7);
        assertEquals(200, respuesta.getStatusCode().value());
        assertEquals("6/10", respuesta.getBody().getResultado());
        assertEquals("1.0:30.0:61", respuesta.getBody().getTiempo());
        assertEquals('B', respuesta.getBody().getRango());
    }

    @Test void obtenerPuntuacionPersonalRechazaSiNoExiste() {
        when(puntuaciones.puntuacionExiste(5, 7)).thenReturn(false);
        var respuesta = controlador.datosMapaUsuario(5, 7);
        assertEquals(409, respuesta.getStatusCode().value());
        verify(puntuaciones, never()).obtenerPuntuacionPersonal(5, 7);
    }

    @Test void obtenerPuntuacionesDeUsuarioInexistenteDevuelveListaVacia() {
        when(usuarios.nombreExiste("ana")).thenReturn(false);
        var respuesta = controlador.datosMapasUsuario("ana");
        assertEquals(409, respuesta.getStatusCode().value());
        assertTrue(respuesta.getBody().isEmpty());
        verify(puntuaciones, never()).obtenerPuntuacionesPersonales(anyString());
    }

    @Test void obtenerPuntuacionesDeUsuarioDevuelveResultadosYTiempoFormateados() {
        List<PuntuacionPersonalPorTipo> datos = List.of(
                new PuntuacionPersonalPorTipo("Europa", "Continente", 6, 4, 61005, 600, 'B'));
        when(usuarios.nombreExiste("ana")).thenReturn(true);
        when(puntuaciones.obtenerPuntuacionesPersonales("ana")).thenReturn(datos);
        when(puntuaciones.obtenerTotal(6, 4)).thenReturn("6/10");
        when(puntuaciones.formatoTiempo(61005)).thenReturn("1.0:1.0:5");
        var respuesta = controlador.datosMapasUsuario("ana");
        assertEquals(200, respuesta.getStatusCode().value());
        assertEquals(1, respuesta.getBody().size());
        assertEquals("Europa", respuesta.getBody().get(0).getNombreMapa());
        assertEquals("Continente", respuesta.getBody().get(0).getTipoMapa());
        assertEquals("6/10", respuesta.getBody().get(0).getResultado());
        assertEquals("1.0:1.0:5", respuesta.getBody().get(0).getTiempo());
        assertEquals('B', respuesta.getBody().get(0).getRango());
    }
}
