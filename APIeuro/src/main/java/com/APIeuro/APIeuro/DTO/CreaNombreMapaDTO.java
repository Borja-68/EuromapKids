package com.APIeuro.APIeuro.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreaNombreMapaDTO {
    private Integer idMapa;

    private String nombreMapa;

    private String idiomaMapa;
}
