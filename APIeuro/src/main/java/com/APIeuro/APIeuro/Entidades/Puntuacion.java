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
    @EmbeddedId
    PuntuacionPK id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("mapaId")
    @JoinColumn(name = "id_mapa")
    private Mapa mapaPuntuacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("usuarioId")
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioPuntuacion;

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

    public void setMapaPuntuacion(Mapa mapaNombre){
        this.mapaPuntuacion = mapaNombre;
        mapaNombre.anadirPuntuacion(this);
    }

    public void setUsuarioPuntuacion(Usuario usuario){
        this.usuarioPuntuacion = usuario;
        usuario.anadirPuntuacion(this);
    }
}
