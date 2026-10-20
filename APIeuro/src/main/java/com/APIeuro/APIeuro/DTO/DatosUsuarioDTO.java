package com.APIeuro.APIeuro.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosUsuarioDTO {

    private Integer idUsuario;

    private String idioma;

    private byte[] imagenUsuario;

    private boolean modoOscuro;
}
