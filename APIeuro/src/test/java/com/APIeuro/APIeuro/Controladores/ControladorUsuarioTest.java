package com.APIeuro.APIeuro.Controladores;

import com.APIeuro.APIeuro.DTO.Login;
import com.APIeuro.APIeuro.Entidades.Usuario;
import com.APIeuro.APIeuro.Servicios.ServicioJwt;
import com.APIeuro.APIeuro.Servicios.ServicioUsuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControladorUsuarioTest {
    private final ServicioUsuario usuarios = mock(ServicioUsuario.class);
    private final ServicioJwt jwt = mock(ServicioJwt.class);
    private final ControladorUsuario controlador = new ControladorUsuario(usuarios, jwt);

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
}
