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

    @ManyToOne
    @MapsId("mapaId")
    @JoinColumn(name = "id_mapa")
    private Mapa mapa;

    @NonNull
    @Column(name = "nombre_mapa")
    private String nombreMapa;

    @Column(name = "idioma", insertable = false, updatable = false)
    private String idiomaMapa;


}


