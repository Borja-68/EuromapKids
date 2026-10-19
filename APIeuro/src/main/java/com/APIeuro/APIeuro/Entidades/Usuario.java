package com.APIeuro.APIeuro.Entidades;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer idUsuario;

    @NonNull
    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @NonNull
    @Column(name = "correo_usuario")
    private String correoUsuario;

    @NonNull
    @Column(name = "contrasena_usuario")
    private String contrasenaUsuario;

    @NonNull
    @Column(name = "idioma")
    private String idioma;

    @Lob
    @Column(name = "imagen_usuario")
    private byte[] imagenUsuario;

    @NonNull
    @Column(name = "modo_oscuro")
    private boolean modoOscuro;

    @OneToMany(mappedBy = "usuarioPuntuacion", cascade = CascadeType.ALL)
    private List<Puntuacion> puntuaciones = new ArrayList<>();

    public  Usuario(String nombreUsuario,String correoUsuario, String contrasenaUsuario, String idioma,byte[] imagenUsuario, boolean modoOscuro){
        this.nombreUsuario=nombreUsuario;
        this.correoUsuario=correoUsuario;
        this.contrasenaUsuario=contrasenaUsuario;
        this.idioma=idioma;
        this.imagenUsuario=imagenUsuario;
        this.modoOscuro=modoOscuro;
    }

    public  Usuario(Integer idUsuario,String nombreUsuario,String correoUsuario, String contrasenaUsuario, String idioma,byte[] imagenUsuario, boolean modoOscuro){
        this.nombreUsuario=nombreUsuario;
        this.correoUsuario=correoUsuario;
        this.contrasenaUsuario=contrasenaUsuario;
        this.idioma=idioma;
        this.imagenUsuario=imagenUsuario;
        this.modoOscuro=modoOscuro;
    }

    public void anadirPuntuacion(Puntuacion puntuacion){
        puntuaciones.add(puntuacion);
        puntuacion.setUsuarioPuntuacion(this);
    }
}
