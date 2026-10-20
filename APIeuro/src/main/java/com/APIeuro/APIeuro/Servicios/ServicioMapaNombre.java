package com.APIeuro.APIeuro.Servicios;

import com.APIeuro.APIeuro.DTO.TipoMapaDTO;
import com.APIeuro.APIeuro.Entidades.Mapa;
import com.APIeuro.APIeuro.Entidades.NombreMapa;
import com.APIeuro.APIeuro.Entidades.NombreMapaPK;
import com.APIeuro.APIeuro.Repositorios.MapaNombreRepository;
import com.APIeuro.APIeuro.Repositorios.MapaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioMapaNombre {

    private final MapaNombreRepository repoNombreMapa;


    public void guardar(NombreMapa nombreMapa) {
        repoNombreMapa.save(nombreMapa);
    }
    public NombreMapa modificar(NombreMapa nombreMapa) {
       return repoNombreMapa.save(nombreMapa);
    }
    public void remove(NombreMapaPK id){
        repoNombreMapa.deleteById(id);
    }

    public NombreMapa obtenerNombreMapa(Integer id,String idioma){
        return repoNombreMapa.obtenerNombreMapa(id,idioma);
    }

    public String obtenerNombreDelMapa(Integer id,String idioma){
        return repoNombreMapa.obtenerNombreDeMapa(id,idioma);
    }

    public boolean idMapaExiste(Integer id,String idioma){
        return repoNombreMapa.IdiomaExiste(id,idioma) !=null;
    }
}
