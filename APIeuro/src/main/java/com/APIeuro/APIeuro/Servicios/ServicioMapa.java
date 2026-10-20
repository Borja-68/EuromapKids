package com.APIeuro.APIeuro.Servicios;

import com.APIeuro.APIeuro.DTO.DatosUsuarioDTO;
import com.APIeuro.APIeuro.DTO.TipoMapaDTO;
import com.APIeuro.APIeuro.Entidades.Mapa;
import com.APIeuro.APIeuro.Entidades.Usuario;
import com.APIeuro.APIeuro.Repositorios.MapaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioMapa {

    private final MapaRepository repoMapa;

    public void guardar(Mapa mapa) {
        repoMapa.save(mapa);
    }
    public Mapa modificar(Mapa usu) {
       return repoMapa.save(usu);
    }

    public void remove(Integer id){
        repoMapa.deleteById(id);
    }

    public List<TipoMapaDTO> obtenerMapasPorTipo(String tipo){
        return repoMapa.obtenerMapasPorTipo(tipo);
    }

    public Mapa obtenerMapaPorId(Integer id){
        return repoMapa.obtenerMapaPorid(id);
    }

    public boolean idExiste(Integer id){
        return repoMapa.idExiste(id) !=null;
    }
}
