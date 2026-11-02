package com.APIeuro.APIeuro.Servicios;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ServicioJwt {
    private final JwtEncoder encoder;

    public ServicioJwt(JwtEncoder encoder) { this.encoder = encoder; }

    public String crearToken(String idUsu) {
        Instant ahora = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("euromapkids-api")
                .issuedAt(ahora)
                .expiresAt(ahora.plusSeconds(3600))
                .subject(idUsu)
                .build();
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        return encoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }
}
