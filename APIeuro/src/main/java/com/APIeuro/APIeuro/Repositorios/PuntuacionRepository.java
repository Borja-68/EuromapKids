package com.APIeuro.APIeuro.Repositorios;

import com.APIeuro.APIeuro.DTO.DatosClasificacionDTO;
import com.APIeuro.APIeuro.DTO.PuntuacionPersonalDTO;
import com.APIeuro.APIeuro.Entidades.Puntuacion;
import com.APIeuro.APIeuro.Entidades.PuntuacionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuntuacionRepository extends JpaRepository<Puntuacion, PuntuacionPK> {

    @Query("select pun.puntuacion from Puntuacion pun join pun.mapaPuntuacion map join pun.usuarioPuntuacion usu where map.idMapa=:idMapa and usu.idUsuario=:idUsuario")
    Integer puntuacionExiste(@Param("idMapa") Integer idMapa,@Param("idUsuario") Integer idUsuario);

    @Query("select pun from Puntuacion pun join pun.mapaPuntuacion map join pun.usuarioPuntuacion usu where map.idMapa=:idMapa and usu.idUsuario=:idUsuario")
    Puntuacion obtenerPuntuacionExiste(@Param("idMapa") Integer idMapa, @Param("idUsuario") Integer idUsuario);

    @Query("select new com.APIeuro.APIeuro.DTO.DatosClasificacionDTO(usu.nombreUsuario, pun.puntuacion, pun.tiempo) from Puntuacion pun join pun.mapaPuntuacion map join pun.usuarioPuntuacion usu where map.idMapa=:idMapa order by pun.puntuacion DESC, pun.tiempo ASC")
    List<DatosClasificacionDTO> listarPuntuacionesMapa(@Param("idMapa") Integer idMapa);

    @Query("select new com.APIeuro.APIeuro.DTO.PuntuacionPersonalDTO(pun.aciertos,pun.fallos,pun.tiempo,pun.puntuacion,pun.rango) from Puntuacion pun join pun.mapaPuntuacion map join pun.usuarioPuntuacion usu where map.idMapa=:idMapa and usu.idUsuario=:idUsuario")
    PuntuacionPersonalDTO obtenerPuntuacionPersonal(@Param("idMapa") Integer idMapa, @Param("idUsuario") Integer idUsuario);
}

