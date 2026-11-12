package com.APIeuro.APIeuro.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosClasificacionDTOEnvio {
    private String nombreUsuario;
    private Integer puntuacion;
    private String tiempo;
}
