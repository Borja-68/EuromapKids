package com.APIeuro.APIeuro.Controladores;
import com.APIeuro.APIeuro.DTO.*;
import com.APIeuro.APIeuro.Entidades.Mapa;
import com.APIeuro.APIeuro.Entidades.Puntuacion;
import com.APIeuro.APIeuro.Entidades.PuntuacionPK;
import com.APIeuro.APIeuro.Entidades.Usuario;
import com.APIeuro.APIeuro.Servicios.ServicioMapa;
import com.APIeuro.APIeuro.Servicios.ServicioPuntuacion;
import com.APIeuro.APIeuro.Servicios.ServicioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/euroAPI/puntuacion")
@RequiredArgsConstructor
public class ControladorPuntuacion {
    private final ServicioPuntuacion servicioPuntuacion;
    private final ServicioUsuario servicioUsuario;
    private final ServicioMapa servicioMapa;


    @PostMapping
    public ResponseEntity<String> crearPuntuacion(@RequestBody CreaPuntuacionDTO puntuacionNueva) {
        if(!servicioUsuario.idExiste(puntuacionNueva.getIdUsuario())) return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario no existe");
        if(!servicioMapa.idExiste(puntuacionNueva.getIdMapa())) return ResponseEntity.status(HttpStatus.CONFLICT).body("Mapa no existe");

        Usuario usu=servicioUsuario.obtenerUsuario(puntuacionNueva.getIdUsuario());
        Mapa map=servicioMapa.obtenerMapaPorId(puntuacionNueva.getIdMapa());
        PuntuacionPK id=new PuntuacionPK(map.getIdMapa(),usu.getIdUsuario());

        Integer puntuacion= servicioPuntuacion.calcularPuntuacion(puntuacionNueva.getAciertos(), puntuacionNueva.getFallos());
        Puntuacion puntuacionCrear= new Puntuacion(puntuacionNueva.getAciertos(),puntuacionNueva.getFallos(),puntuacionNueva.getTiempo(),puntuacion,servicioPuntuacion.obtenerRango(puntuacion));
        puntuacionCrear.setId(id);
        servicioPuntuacion.guardar(puntuacionCrear);

        return ResponseEntity.status(HttpStatus.OK).body("Creado con éxito");
    }

    @DeleteMapping("/{idMapa}-{idUsuario}")
    public ResponseEntity<String> borrarPuntuacion(@PathVariable Integer idMapa,@PathVariable Integer idUsuario) {
        if(!servicioPuntuacion.puntuacionExiste(idMapa,idUsuario))return ResponseEntity.status(HttpStatus.CONFLICT).body("Puntuación no eliminada, no existe");
        servicioPuntuacion.remove(new PuntuacionPK(idMapa,idUsuario));
        return ResponseEntity.status(HttpStatus.OK).body("Puntuación eliminado");
    }

    @PatchMapping
    public ResponseEntity<String> modificarPuntuacion(@RequestBody CreaPuntuacionDTO puntuacionNueva) {
        if(!servicioPuntuacion.puntuacionExiste(puntuacionNueva.getIdMapa(), puntuacionNueva.getIdUsuario()))return ResponseEntity.status(HttpStatus.CONFLICT).body("Puntuación no existe");
        Puntuacion puntuacionVieja= servicioPuntuacion.obtenerPuntuacion(puntuacionNueva.getIdMapa(),puntuacionNueva.getIdUsuario());
        Integer puntosNuevos=servicioPuntuacion.calcularPuntuacion(puntuacionNueva.getAciertos(), puntuacionNueva.getFallos());

        if(puntosNuevos<puntuacionVieja.getPuntuacion()){
            puntuacionVieja.setAciertos(puntuacionNueva.getAciertos());
            puntuacionVieja.setFallos(puntuacionNueva.getFallos());
            puntuacionVieja.setPuntuacion(puntosNuevos);
            puntuacionVieja.setRango(servicioPuntuacion.obtenerRango(puntosNuevos));
            if(puntuacionNueva.getTiempo()<puntuacionVieja.getTiempo())puntuacionVieja.setTiempo(puntuacionNueva.getTiempo());
        }
        servicioPuntuacion.modificar(puntuacionVieja);
        return ResponseEntity.status(HttpStatus.OK).body("Puntuacion anterior fue mejor, no modificado");
    }

    @GetMapping("/{idMapa}")
    public ResponseEntity<List<DatosClasificacionDTO>> obtenerClasificacion(@PathVariable Integer idMapa) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(servicioPuntuacion.obtenerClasificacion(idMapa));
    }

    @GetMapping("/{idMapa}-{idUsuario}")
    public ResponseEntity<PuntuacionPersonalDTO> datosUsuario(@PathVariable Integer idMapa,@PathVariable Integer idUsuario) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(servicioPuntuacion.obtenerPuntuacionPersonal(idMapa,idUsuario));
    }
}
