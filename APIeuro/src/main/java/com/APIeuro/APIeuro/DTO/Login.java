package com.APIeuro.APIeuro.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Login {
    private String nombreUsuario;

    private String correoUsuario;

    private String contrasenaUsuario;

}
