package com.APIeuro.APIeuro.Servicios;

import com.APIeuro.APIeuro.Entidades.Usuario;
import com.APIeuro.APIeuro.Repositorios.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServicioUsuarioTest {
    private final UsuarioRepository repo = mock(UsuarioRepository.class);
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final ServicioUsuario servicio = new ServicioUsuario(repo, encoder);

    @Test void guardarPersisteContrasenaHasheada() {
        Usuario usuario = new Usuario("ana", "ana@example.com", "secreta", "es", null, false);
        servicio.guardar(usuario);
        assertNotEquals("secreta", usuario.getContrasenaUsuario());
        assertTrue(encoder.matches("secreta", usuario.getContrasenaUsuario()));
        verify(repo).save(usuario);
    }

    @Test void comprobarContrasenaComparaHash() {
        String hash = encoder.encode("secreta");
        assertTrue(servicio.verificarContrasena("secreta", hash));
        assertFalse(servicio.verificarContrasena("incorrecta", hash));
    }
}
