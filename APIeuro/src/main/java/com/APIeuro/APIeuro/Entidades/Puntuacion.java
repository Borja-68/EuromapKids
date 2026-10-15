package com.APIeuro.APIeuro.Entidades;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Puntuacion {
    @Id
    @Column(name ="id_mapa")
    private Integer idMapa;


    @Id
    @Column(name = "id_usuario")
    private String idUsuario;

    @NonNull
    @Column(name = "correo_usuario")
    private String correoUsuario;

    @NonNull
    @Column(name = "tiempo")
    private LocalTime tiempo;

    @NonNull
    @Column(name = "idioma")
    private String idioma;

    @NonNull
    @Column(name = "aciertos")
    private Integer aciertos;

    @NonNull
    @Column(name = "fallos")
    private Integer fallos;

    @NonNull
    @Column(name = "puntuacion")
    private Integer puntuacion;

    @NonNull
    @Column(name = "rango")
    private char rango;

    @ManyToOne
    @MapsId("idMapa")
    @JoinColumn(name = "id_mapa", nullable = false)
    private Mapa mapa;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
