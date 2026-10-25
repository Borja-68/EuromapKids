package com.APIeuro.APIeuro.Controladores;
import com.APIeuro.APIeuro.DTO.CreaUsuarioDTO;
import com.APIeuro.APIeuro.DTO.DatosUsuarioDTO;
import com.APIeuro.APIeuro.DTO.Login;
import com.APIeuro.APIeuro.DTO.ModificaUsuarioDTO;
import com.APIeuro.APIeuro.Entidades.Usuario;
import com.APIeuro.APIeuro.Servicios.ServicioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/euroAPI/usuario")
@RequiredArgsConstructor
public class ControladorUsuario {
    private final ServicioUsuario servicioUsuario;

    @PostMapping
    public ResponseEntity<String> crearUsuario(@RequestBody CreaUsuarioDTO usuarioNuevo) {
        if(servicioUsuario.nombreExiste(usuarioNuevo.getNombreUsuario())) return ResponseEntity.status(HttpStatus.CONFLICT).body("Nombre usuario ya existente");
        if(servicioUsuario.correoExiste(usuarioNuevo.getCorreoUsuario())) return ResponseEntity.status(HttpStatus.CONFLICT).body("Correo usuario ya existente");
        servicioUsuario.guardar(new Usuario(usuarioNuevo.getNombreUsuario(), usuarioNuevo.getCorreoUsuario(),
                usuarioNuevo.getContrasenaUsuario(), usuarioNuevo.getIdioma(),
                usuarioNuevo.getImagenUsuario(), usuarioNuevo.isModoOscuro()));
        return ResponseEntity.status(HttpStatus.OK).body("Creado con éxito");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable Integer id) {
        if(!servicioUsuario.idExiste(id)) return ResponseEntity.status(HttpStatus.CONFLICT).body("Id de usuario no existe");
        servicioUsuario.remove(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> modificarUsuario(@PathVariable Integer id,@RequestBody ModificaUsuarioDTO usuarioNuevo) {
        if(!servicioUsuario.idExiste(id)) return ResponseEntity.status(HttpStatus.CONFLICT).body("Id de usuario no existe");
        Usuario usuarioAModificar=servicioUsuario.obtenerUsuarioPorId(id);
        if(!usuarioAModificar.getNombreUsuario().equals(usuarioNuevo.getNombreUsuario()) && servicioUsuario.nombreExiste(usuarioNuevo.getNombreUsuario())) return ResponseEntity.status(HttpStatus.CONFLICT).body("Nombre usuario ya existente");
        if(!usuarioNuevo.getNombreUsuario().equals(usuarioNuevo.getNombreUsuario()))usuarioAModificar.setNombreUsuario(usuarioNuevo.getNombreUsuario());
        if(!servicioUsuario.verificarContrasena(usuarioNuevo.getContrasenaUsuario(),usuarioAModificar.getContrasenaUsuario())){
            usuarioAModificar.setContrasenaUsuario(usuarioNuevo.getContrasenaUsuario());
            servicioUsuario.encriptarContrasena(usuarioAModificar);
        }

        if(usuarioNuevo.getImagenUsuario()!=usuarioAModificar.getImagenUsuario())usuarioAModificar.setImagenUsuario(usuarioNuevo.getImagenUsuario());
        if(!usuarioNuevo.getIdioma().equals(usuarioAModificar.getIdioma()))usuarioAModificar.setIdioma(usuarioNuevo.getIdioma());
        usuarioAModificar.setModoOscuro(usuarioNuevo.isModoOscuro());

        servicioUsuario.modificar(usuarioAModificar);
        return ResponseEntity.status(HttpStatus.OK).body("Modificado con éxito");
    }

    @GetMapping("/{nombreOCorreo}")
    public ResponseEntity<DatosUsuarioDTO> getDatos(@PathVariable String nombreOCorreo) {
        if(servicioUsuario.correoExiste(nombreOCorreo)){
            return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.obtenerDatosUsuarioPorCorreo(nombreOCorreo));
        }
        if(servicioUsuario.nombreExiste(nombreOCorreo)){
            return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.obtenerDatosUsuarioPorNombre(nombreOCorreo));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new DatosUsuarioDTO());
    }

    @GetMapping("/login/{nombreOCorreo}-{contrasena}")
    public ResponseEntity<Boolean> login(@PathVariable String nombreOCorreo,@PathVariable String contrasena) {
        Usuario usu;
        if(servicioUsuario.correoExiste(nombreOCorreo)){
            usu=servicioUsuario.obtenerUsuarioPorCorreo(nombreOCorreo);
            if(servicioUsuario.verificarContrasena(contrasena,usu.getContrasenaUsuario()))return ResponseEntity.status(HttpStatus.OK).body(true);
            else return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }

        if(servicioUsuario.nombreExiste(nombreOCorreo)){
            usu=servicioUsuario.obtenerUsuarioPorNombre(nombreOCorreo);
            if(servicioUsuario.verificarContrasena(contrasena,usu.getContrasenaUsuario()))return ResponseEntity.status(HttpStatus.OK).body(true);
            else return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
    }
}
