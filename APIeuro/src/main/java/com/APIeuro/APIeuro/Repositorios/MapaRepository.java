package com.APIeuro.APIeuro.Repositorios;

import com.APIeuro.APIeuro.DTO.DatosUsuarioDTO;
import com.APIeuro.APIeuro.DTO.TipoMapaDTO;
import com.APIeuro.APIeuro.Entidades.Mapa;
import com.APIeuro.APIeuro.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapaRepository extends JpaRepository<Mapa, Integer> {

    @Query("select map from Mapa map where map.idMapa=:id")
    Mapa obtenerMapaPorid(@Param("id") Integer id);

    @Query("select map.idMapa from Mapa map where map.idMapa=:id")
    String idExiste(@Param("id") Integer id);

    @Query("select new com.APIeuro.APIeuro.DTO.TipoMapaDTO(map.idMapa,map.imagen) from Mapa map where map.tipoMapa=:tipo")
    List<TipoMapaDTO> obtenerMapasPorTipo(@Param("tipo") String tipo);

}

