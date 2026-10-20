package com.APIeuro.APIeuro.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreaMapaDTO {
    private String tipoMapa;

    private byte[] imagen;

}
