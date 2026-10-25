package com.APIeuro.APIeuro.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosClasificacionDTO {
   private String nombreUsuario;
   private Integer puntuacion;
   private long tiempo;
}
