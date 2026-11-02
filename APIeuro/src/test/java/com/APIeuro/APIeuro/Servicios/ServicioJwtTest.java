package com.APIeuro.APIeuro.Servicios;

import com.APIeuro.APIeuro.Config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import javax.crypto.SecretKey;
import static org.junit.jupiter.api.Assertions.*;

class ServicioJwtTest {
    @Test void tokenEmitidoSeValidaYExpiraEnUnaHora() {
        SecurityConfig config = new SecurityConfig();
        SecretKey key = config.jwtSecretKey("una-clave-de-prueba-con-mas-de-32-caracteres");
        ServicioJwt servicio = new ServicioJwt(config.jwtEncoder(key));
        JwtDecoder decoder = config.jwtDecoder(key);
        var jwt = decoder.decode(servicio.crearToken("42"));
        assertEquals("42", jwt.getSubject());
        assertEquals("euromapkids-api", jwt.getClaimAsString("iss"));
        assertEquals(3600, jwt.getExpiresAt().getEpochSecond() - jwt.getIssuedAt().getEpochSecond());
    }

    @Test void rechazaClaveDebil() {
        assertThrows(IllegalArgumentException.class, () -> new SecurityConfig().jwtSecretKey("corta"));
    }
}
