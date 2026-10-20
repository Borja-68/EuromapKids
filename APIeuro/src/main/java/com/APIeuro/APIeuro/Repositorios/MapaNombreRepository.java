package com.APIeuro.APIeuro.Repositorios;

import com.APIeuro.APIeuro.DTO.TipoMapaDTO;
import com.APIeuro.APIeuro.Entidades.Mapa;
import com.APIeuro.APIeuro.Entidades.NombreMapa;
import com.APIeuro.APIeuro.Entidades.NombreMapaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapaNombreRepository extends JpaRepository<NombreMapa, NombreMapaPK> {

    @Query("select mapNom.idiomaMapa from NombreMapa mapNom join mapNom.mapa map where map.idMapa=:id and mapNom.idiomaMapa=:idioma")
    String IdiomaExiste(@Param("id") Integer id,@Param("idioma") String idioma);

    @Query("select mapNom.nombreMapa from NombreMapa mapNom join mapNom.mapa map where map.idMapa=:id and mapNom.idiomaMapa=:idioma")
    String obtenerNombreDeMapa(@Param("id") Integer id,@Param("idioma") String idioma);

    @Query("select mapNom from NombreMapa mapNom join mapNom.mapa map where map.idMapa=:id and mapNom.idiomaMapa=:idioma")
    NombreMapa obtenerNombreMapa(@Param("id") Integer id, @Param("idioma") String idioma);
}

