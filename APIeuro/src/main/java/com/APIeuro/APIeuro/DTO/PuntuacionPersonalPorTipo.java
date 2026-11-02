package com.APIeuro.APIeuro.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PuntuacionPersonalPorTipo {
    private String nombreMapa;
    private String tipoMapa;
    private Integer aciertos;
    private Integer fallos;
    private long tiempo;
    private Integer puntuacion;
    private char rango;
}
