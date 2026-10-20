package com.APIeuro.APIeuro.Servicios;

import com.APIeuro.APIeuro.DTO.DatosUsuarioDTO;
import com.APIeuro.APIeuro.Entidades.Usuario;
import com.APIeuro.APIeuro.Repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioUsuario {

    private final UsuarioRepository repoUsuario;

    public void guardar(Usuario usu) {
        repoUsuario.save(usu);
    }
    public Usuario modificar(Usuario usu) {
       return repoUsuario.save(usu);
    }

    public void remove(Integer id){
        repoUsuario.deleteById(id);
    }

    public boolean correoExiste(String correo){
        return repoUsuario.correoExiste(correo) !=null;
    }

    public boolean nombreExiste(String nombre){
        return repoUsuario.nombreExiste(nombre) !=null;
    }

    public boolean idExiste(Integer id){
        return repoUsuario.idExiste(id) !=null;
    }

    public Usuario obtenerUsuario(Integer id){
        return repoUsuario.obtenerUsuario(id);
    }

    public DatosUsuarioDTO obtenerDatosUsuarioPorNombre(String nombre){
        return repoUsuario.obtenerDatosUsuarioPorNombre(nombre);
    }

    public DatosUsuarioDTO obtenerDatosUsuarioPorCorreo(String correo){
        return repoUsuario.obtenerDatosUsuarioPorCorreo(correo);
    }
}
