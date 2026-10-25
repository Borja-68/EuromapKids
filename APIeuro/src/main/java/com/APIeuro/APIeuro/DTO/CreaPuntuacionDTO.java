package com.APIeuro.APIeuro.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreaPuntuacionDTO {
    private  Integer idMapa;

    private  Integer idUsuario;

    private Integer aciertos;

    private Integer fallos;

    private long tiempo;

}
