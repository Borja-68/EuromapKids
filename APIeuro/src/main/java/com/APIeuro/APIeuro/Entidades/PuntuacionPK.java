package com.APIeuro.APIeuro.Entidades;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PuntuacionPK {
    private Integer mapaId;
    private Integer usuarioId;
}
