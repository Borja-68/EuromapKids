package com.APIeuro.APIeuro.Entidades;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NombreMapa {
    @Id
    @Column(name ="id_mapa")
    private Integer idMapa;

    @Id
    @Column(name = "nombre_mapa")
    private String nombreMapa;

    @NonNull
    @Column(name = "idioma")
    private String idioma;

    @ManyToOne
    @MapsId("idMapa")
    @JoinColumn(name = "id_mapa")
    private Mapa mapa;

    public void anadirMapa(Mapa mapa){
        this.mapa=mapa;
        mapa.anhadirNombre(this);
    }
}
