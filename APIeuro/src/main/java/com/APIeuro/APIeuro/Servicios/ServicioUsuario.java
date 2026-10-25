package com.APIeuro.APIeuro.Servicios;

import com.APIeuro.APIeuro.DTO.DatosUsuarioDTO;
import com.APIeuro.APIeuro.Entidades.Usuario;
import com.APIeuro.APIeuro.Repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicioUsuario {

    private final UsuarioRepository repoUsuario;

    private final BCryptPasswordEncoder encriptador;

    public void encriptarContrasena(Usuario usu){
        usu.setContrasenaUsuario(encriptador.encode(usu.getContrasenaUsuario()));
    }
    public void guardar(Usuario usu) {
        encriptarContrasena(usu);
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

    public Usuario obtenerUsuarioPorId(Integer id){
        return repoUsuario.obtenerUsuarioPorId(id);
    }

    public Usuario obtenerUsuarioPorCorreo(String correo){
        return repoUsuario.obtenerUsuarioPorCorreo(correo);
    }

    public Usuario obtenerUsuarioPorNombre(String nombre){
        return repoUsuario.obtenerUsuarioPorNombre(nombre);
    }

    public boolean verificarContrasena(String contrasenaEnviada, String contrasenaEncriptada) {
        return encriptador.matches(contrasenaEnviada, contrasenaEncriptada);
    }

    public DatosUsuarioDTO obtenerDatosUsuarioPorNombre(String nombre){
        return repoUsuario.obtenerDatosUsuarioPorNombre(nombre);
    }

    public DatosUsuarioDTO obtenerDatosUsuarioPorCorreo(String correo){
        return repoUsuario.obtenerDatosUsuarioPorCorreo(correo);
    }
}
