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
        if(servicioUsuario.correoExiste(usuarioNuevo.getCorreoUsuario())) return ResponseEntity.status(HttpStatus.CONFLICT).body("Nombre usuario ya existente");
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
        Usuario usuarioAModificar=servicioUsuario.obtenerUsuario(id);
        if(!usuarioAModificar.getNombreUsuario().equals(usuarioAModificar.getNombreUsuario()) && servicioUsuario.nombreExiste(usuarioNuevo.getNombreUsuario())) return ResponseEntity.status(HttpStatus.CONFLICT).body("Nombre usuario ya existente");
        if(usuarioNuevo.getNombreUsuario()!=null)usuarioAModificar.setNombreUsuario(usuarioNuevo.getNombreUsuario());
        if(usuarioNuevo.getContrasenaUsuario()!=null)usuarioAModificar.setContrasenaUsuario(usuarioNuevo.getContrasenaUsuario());
        if(usuarioNuevo.getImagenUsuario()!=null)usuarioAModificar.setImagenUsuario(usuarioNuevo.getImagenUsuario());
        if(usuarioNuevo.getIdioma()!=null)usuarioAModificar.setIdioma(usuarioNuevo.getIdioma());
        usuarioAModificar.setModoOscuro(usuarioNuevo.isModoOscuro());

        servicioUsuario.modificar(usuarioAModificar);
        return ResponseEntity.status(HttpStatus.OK).body("Modificado con éxito");
    }

    @GetMapping("/{nombreOCorreo}")
    public ResponseEntity<DatosUsuarioDTO> login(@PathVariable String nombreOCorreo) {
        if(servicioUsuario.correoExiste(nombreOCorreo)){
            return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.obtenerDatosUsuarioPorCorreo(nombreOCorreo));
        }
        if(servicioUsuario.nombreExiste(nombreOCorreo)){
            return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.obtenerDatosUsuarioPorNombre(nombreOCorreo));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new DatosUsuarioDTO());
    }

    @GetMapping("/login")
    public ResponseEntity<Boolean> datosUsuario(@RequestBody Login loginInfo) {
        if(loginInfo.getCorreoUsuario()!=null && servicioUsuario.correoExiste(loginInfo.getCorreoUsuario()))return ResponseEntity.status(HttpStatus.OK).body(true);
        if(loginInfo.getNombreUsuario()!=null && servicioUsuario.nombreExiste(loginInfo.getNombreUsuario()))return ResponseEntity.status(HttpStatus.OK).body(true);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
    }
}
