package com.APIeuro.APIeuro.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreaUsuarioDTO {
    private String nombreUsuario;

    private String correoUsuario;

    private String contrasenaUsuario;

    private String idioma;

    private byte[] imagenUsuario;

    private boolean modoOscuro;
}
