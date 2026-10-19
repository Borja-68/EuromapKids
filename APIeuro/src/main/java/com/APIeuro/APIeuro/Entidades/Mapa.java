package com.APIeuro.APIeuro.Entidades;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mapa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer idMapa;

    @NonNull
    @Column(name = "tipo_mapa")
    private String tipoMapa;

    @Lob
    @Column(name = "imagen_mapa")
    private byte[] imagen;

    @OneToMany(mappedBy = "mapaNombre", cascade = CascadeType.ALL)
    private List<NombreMapa> nombres = new ArrayList<>();

    @OneToMany(mappedBy = "mapaPuntuacion", cascade = CascadeType.ALL)
    private List<Puntuacion> puntuaciones = new ArrayList<>();

    public void anadirNombre(NombreMapa nombreMapa){
        nombres.add(nombreMapa);
        nombreMapa.setMapaNombre(this);
    }

    public void anadirPuntuacion(Puntuacion puntuacion){
        puntuaciones.add(puntuacion);
        puntuacion.setMapaPuntuacion(this);
    }

}
