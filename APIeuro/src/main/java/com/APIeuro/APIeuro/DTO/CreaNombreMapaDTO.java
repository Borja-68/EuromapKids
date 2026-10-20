package com.APIeuro.APIeuro.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreaNombreMapaDTO {
    private Integer idMapa;

    private String nombreMapa;

    private String idiomaMapa;
}
