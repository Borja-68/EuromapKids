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

    @OneToMany(mappedBy = "mapa")
    private List<NombreMapa> nombres = new ArrayList<>();

    public void anhadirNombre(NombreMapa mapa){
        this.nombres.add(mapa);
        mapa.setMapa(this);
    }
}
