package com.APIeuro.APIeuro.Entidades;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class NombreMapa {
    @EmbeddedId
    NombreMapaPK id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("mapaId")
    @JoinColumn(name = "id_mapa")
    private Mapa mapa;

    @NonNull
    @Column(name = "nombre_mapa")
    private String nombreMapa;

    @MapsId("idioma")
    @Column(name = "idioma")
    private String idiomaMapa;

    public void setMapaNombre(Mapa mapa){
        this.mapa = mapa;
        mapa.anadirNombre(this);
    }
}


