package com.APIeuro.APIeuro.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class DatosClasificacionDTO {
   private String nombreUsuario;
   private Integer puntuacion;
   private long tiempo;
}
