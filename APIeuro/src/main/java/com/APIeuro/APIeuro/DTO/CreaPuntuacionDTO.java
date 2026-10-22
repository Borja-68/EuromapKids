package com.APIeuro.APIeuro.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class CreaPuntuacionDTO {
    private  Integer idMapa;

    private  Integer idUsuario;

    private long tiempo;

    private Integer aciertos;

    private Integer fallos;

}
