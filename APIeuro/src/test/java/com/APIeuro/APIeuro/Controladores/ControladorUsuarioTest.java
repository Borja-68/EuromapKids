package com.APIeuro.APIeuro.Controladores;

import com.APIeuro.APIeuro.DTO.*;
import com.APIeuro.APIeuro.Entidades.Usuario;
import com.APIeuro.APIeuro.Servicios.ServicioJwt;
import com.APIeuro.APIeuro.Servicios.ServicioUsuario;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentCaptor.forClass;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControladorUsuarioTest {
    private final ServicioUsuario usuarios = mock(ServicioUsuario.class);
    private final ServicioJwt jwt = mock(ServicioJwt.class);
    private final ControladorUsuario controlador = new ControladorUsuario(usuarios, jwt);

    private Usuario usuario(Integer id, String nombre) {
        return new Usuario(id, nombre, nombre + "@example.com", "hash", "es", null, false, new java.util.ArrayList<>());
    }

    @Test void registroRechazaNombreDuplicado() {
        when(usuarios.nombreExiste("ana")).thenReturn(true);
        var respuesta = controlador.crearUsuario(new CreaUsuarioDTO("ana", "ana@example.com", "pw", "es", null, false));
        assertEquals(409, respuesta.getStatusCode().value());
        verify(usuarios, never()).guardar(any());
    }

    @Test void registroRechazaCorreoDuplicado() {
        when(usuarios.nombreExiste("ana")).thenReturn(false);
        when(usuarios.correoExiste("ana@example.com")).thenReturn(true);
        var respuesta = controlador.crearUsuario(new CreaUsuarioDTO("ana", "ana@example.com", "pw", "es", null, false));
        assertEquals(409, respuesta.getStatusCode().value());
        verify(usuarios, never()).guardar(any());
    }

    @Test void registroGuardaUsuarioNuevo() {
        CreaUsuarioDTO dto = new CreaUsuarioDTO("ana", "ana@example.com", "pw", "es", new byte[]{1}, true);
        when(usuarios.nombreExiste("ana")).thenReturn(false);
        when(usuarios.correoExiste("ana@example.com")).thenReturn(false);
        var respuesta = controlador.crearUsuario(dto);
        assertEquals(200, respuesta.getStatusCode().value());
        var captor = forClass(Usuario.class);
        verify(usuarios).guardar(captor.capture());
        assertEquals("ana", captor.getValue().getNombreUsuario());
        assertEquals("ana@example.com", captor.getValue().getCorreoUsuario());
        assertEquals("pw", captor.getValue().getContrasenaUsuario());
    }

    @Test void borrarUsuarioRechazaIdInexistente() {
        when(usuarios.idExiste(4)).thenReturn(false);
        assertEquals(409, controlador.borrarUsuario(4).getStatusCode().value());
        verify(usuarios, never()).remove(anyInt());
    }

    @Test void borrarUsuarioEliminaSiExiste() {
        when(usuarios.idExiste(4)).thenReturn(true);
        assertEquals(200, controlador.borrarUsuario(4).getStatusCode().value());
        verify(usuarios).remove(4);
    }

    @Test void modificarUsuarioRechazaIdInexistente() {
        when(usuarios.idExiste(4)).thenReturn(false);
        assertEquals(409, controlador.modificarUsuario(4, new ModificaUsuarioDTO()).getStatusCode().value());
        verify(usuarios, never()).modificar(any());
    }

    @Test void modificarUsuarioRechazaNombreDuplicado() {
        when(usuarios.idExiste(4)).thenReturn(true);
        when(usuarios.obtenerUsuarioPorId(4)).thenReturn(usuario(4, "ana"));
        when(usuarios.nombreExiste("bea")).thenReturn(true);
        var respuesta = controlador.modificarUsuario(4, new ModificaUsuarioDTO("bea", "pw", "es", null, false));
        assertEquals(409, respuesta.getStatusCode().value());
        verify(usuarios, never()).modificar(any());
    }

    @Test void modificarUsuarioActualizaNombreYPreferencias() {
        Usuario existente = usuario(4, "ana");
        byte[] imagen = {2, 3};
        when(usuarios.idExiste(4)).thenReturn(true);
        when(usuarios.obtenerUsuarioPorId(4)).thenReturn(existente);
        when(usuarios.nombreExiste("bea")).thenReturn(false);
        when(usuarios.verificarContrasena("pw", "hash")).thenReturn(true);

        var respuesta = controlador.modificarUsuario(4, new ModificaUsuarioDTO("bea", "pw", "en", imagen, true));

        assertEquals(200, respuesta.getStatusCode().value());
        assertEquals("bea", existente.getNombreUsuario());
        assertEquals("en", existente.getIdioma());
        assertArrayEquals(imagen, existente.getImagenUsuario());
        assertTrue(existente.isModoOscuro());
        verify(usuarios).modificar(existente);
    }

    @Test void modificarUsuarioReencriptaLaContrasenaSiHaCambiado() {
        Usuario existente = usuario(4, "ana");
        when(usuarios.idExiste(4)).thenReturn(true);
        when(usuarios.obtenerUsuarioPorId(4)).thenReturn(existente);
        when(usuarios.verificarContrasena("nueva", "hash")).thenReturn(false);
        var respuesta = controlador.modificarUsuario(4, new ModificaUsuarioDTO("ana", "nueva", "es", null, false));
        assertEquals(200, respuesta.getStatusCode().value());
        verify(usuarios).encriptarContrasena(existente);
        verify(usuarios).modificar(existente);
    }

    @Test void consultarUsuarioPorCorreoDevuelveSusDatos() {
        DatosUsuarioDTO datos = new DatosUsuarioDTO(4, "es", null, false);
        when(usuarios.correoExiste("ana@example.com")).thenReturn(true);
        when(usuarios.obtenerDatosUsuarioPorCorreo("ana@example.com")).thenReturn(datos);
        var respuesta = controlador.getDatos("ana@example.com");
        assertEquals(200, respuesta.getStatusCode().value());
        assertSame(datos, respuesta.getBody());
    }

    @Test void consultarUsuarioPorNombreDevuelveSusDatos() {
        DatosUsuarioDTO datos = new DatosUsuarioDTO(4, "es", null, false);
        when(usuarios.correoExiste("ana")).thenReturn(false);
        when(usuarios.nombreExiste("ana")).thenReturn(true);
        when(usuarios.obtenerDatosUsuarioPorNombre("ana")).thenReturn(datos);
        var respuesta = controlador.getDatos("ana");
        assertEquals(200, respuesta.getStatusCode().value());
        assertSame(datos, respuesta.getBody());
    }

    @Test void consultarUsuarioInexistenteDevuelveConflicto() {
        when(usuarios.correoExiste("nadie")).thenReturn(false);
        when(usuarios.nombreExiste("nadie")).thenReturn(false);
        assertEquals(409, controlador.getDatos("nadie").getStatusCode().value());
    }

    @Test void loginCorrectoDevuelveJwtPorCorreo() {
        Usuario usuario = new Usuario(7, "ana", "ana@example.com", "hash", "es", null, false, new java.util.ArrayList<>());
        when(usuarios.correoExiste("ana@example.com")).thenReturn(true);
        when(usuarios.obtenerUsuarioPorCorreo("ana@example.com")).thenReturn(usuario);
        when(usuarios.verificarContrasena("secreta", "hash")).thenReturn(true);
        when(jwt.crearToken("7")).thenReturn("token-firmado");
        Login login = new Login(null, "ana@example.com", "secreta");

        var respuesta = controlador.login(login);

        assertEquals(200, respuesta.getStatusCode().value());
        assertEquals("token-firmado", ((java.util.Map<?, ?>) respuesta.getBody()).get("token"));
    }

    @Test void loginIncorrectoNoEmiteToken() {
        when(usuarios.correoExiste("ana@example.com")).thenReturn(false);
        when(usuarios.nombreExiste("ana@example.com")).thenReturn(false);
        var respuesta = controlador.login(new Login(null, "ana@example.com", "mal"));
        assertEquals(401, respuesta.getStatusCode().value());
        verifyNoInteractions(jwt);
    }

    @Test void loginCorrectoTambienAceptaNombreDeUsuario() {
        Usuario usuario = usuario(7, "ana");
        when(usuarios.correoExiste("ana")).thenReturn(false);
        when(usuarios.nombreExiste("ana")).thenReturn(true);
        when(usuarios.obtenerUsuarioPorNombre("ana")).thenReturn(usuario);
        when(usuarios.verificarContrasena("secreta", "hash")).thenReturn(true);
        when(jwt.crearToken("7")).thenReturn("token-firmado");
        var respuesta = controlador.login(new Login("ana", null, "secreta"));
        assertEquals(200, respuesta.getStatusCode().value());
        assertEquals("token-firmado", ((java.util.Map<?, ?>) respuesta.getBody()).get("token"));
    }

    @Test void loginConContrasenaIncorrectaDevuelve401() {
        when(usuarios.correoExiste("ana@example.com")).thenReturn(true);
        when(usuarios.obtenerUsuarioPorCorreo("ana@example.com")).thenReturn(usuario(7, "ana"));
        when(usuarios.verificarContrasena("mal", "hash")).thenReturn(false);
        var respuesta = controlador.login(new Login(null, "ana@example.com", "mal"));
        assertEquals(401, respuesta.getStatusCode().value());
        verifyNoInteractions(jwt);
    }
}
