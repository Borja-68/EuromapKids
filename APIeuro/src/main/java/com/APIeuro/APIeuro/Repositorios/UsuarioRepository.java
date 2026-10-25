package com.APIeuro.APIeuro.Repositorios;

import com.APIeuro.APIeuro.DTO.DatosUsuarioDTO;
import com.APIeuro.APIeuro.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select us.nombreUsuario from Usuario us where us.nombreUsuario=:nombre")
    String nombreExiste(@Param("nombre") String nombre);

    @Query("select us.correoUsuario from Usuario us where us.correoUsuario=:correo")
    String correoExiste(@Param("correo") String correo);

    @Query("select us.idUsuario from Usuario us where us.idUsuario=:id")
    String idExiste(@Param("id") Integer id);

    @Query("select us from Usuario us where us.id=:id")
    Usuario obtenerUsuarioPorId(@Param("id") Integer id);

    @Query("select us from Usuario us where us.nombreUsuario=:nombre")
    Usuario obtenerUsuarioPorNombre(@Param("nombre") String nombre);

    @Query("select us from Usuario us where us.correoUsuario=:correo")
    Usuario obtenerUsuarioPorCorreo(@Param("correo") String nombre);

    @Query("select new com.APIeuro.APIeuro.DTO.DatosUsuarioDTO(us.idUsuario,us.idioma,us.imagenUsuario,us.modoOscuro) from Usuario us where us.nombreUsuario=:nombre")
    DatosUsuarioDTO obtenerDatosUsuarioPorNombre(@Param("nombre") String nombre);

    @Query("select new com.APIeuro.APIeuro.DTO.DatosUsuarioDTO(us.idUsuario,us.idioma,us.imagenUsuario,us.modoOscuro) from Usuario us where us.correoUsuario=:correo")
    DatosUsuarioDTO obtenerDatosUsuarioPorCorreo(@Param("correo") String correo);
}

