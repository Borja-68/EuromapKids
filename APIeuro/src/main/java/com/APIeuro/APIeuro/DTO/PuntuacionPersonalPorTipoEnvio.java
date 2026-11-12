package com.APIeuro.APIeuro.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PuntuacionPersonalPorTipoEnvio {
    private String nombreMapa;
    private String tipoMapa;
    private String resultado;
    private String tiempo;
    private char rango;
}
