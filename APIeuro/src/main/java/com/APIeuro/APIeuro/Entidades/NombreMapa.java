package com.APIeuro.APIeuro.Entidades;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NombreMapa {
    @EmbeddedId
    NombreMapaPK id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("mapaId")
    @JoinColumn(name = "id_mapa")
    private Mapa mapaNombre;


    @MapsId("nombreMapa")
    @Column(name = "nombre_mapa")
    private String nombre;

    @NonNull
    @Column(name = "idioma")
    private String idioma;

    public void setMapaNombre(Mapa mapaNombre){
        this.mapaNombre = mapaNombre;
        mapaNombre.anadirNombre(this);
    }
}


