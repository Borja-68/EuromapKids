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
@RequiredArgsConstructor
public class Puntuacion {
    @EmbeddedId
    PuntuacionPK id;

    @ManyToOne
    @MapsId("mapaId")
    @JoinColumn(name = "id_mapa")
    private Mapa mapaPuntuacion;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioPuntuacion;

    @NonNull
    @Column(name = "aciertos")
    private Integer aciertos;

    @NonNull
    @Column(name = "fallos")
    private Integer fallos;

    @NonNull
    @Column(name = "tiempo")
    private long tiempo;

    @NonNull
    @Column(name = "puntuacion")
    private Integer puntuacion;

    @NonNull
    @Column(name = "rango")
    private char rango;
}
