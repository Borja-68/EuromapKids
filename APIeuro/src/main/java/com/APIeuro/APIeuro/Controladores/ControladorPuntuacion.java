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

import java.util.ArrayList;
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

        Usuario usu=servicioUsuario.obtenerUsuarioPorId(puntuacionNueva.getIdUsuario());
        Mapa map=servicioMapa.obtenerMapaPorId(puntuacionNueva.getIdMapa());
        PuntuacionPK id=new PuntuacionPK(map.getIdMapa(),usu.getIdUsuario());

        Integer puntuacion= servicioPuntuacion.calcularPuntuacion(puntuacionNueva.getAciertos(), puntuacionNueva.getFallos());
        Puntuacion puntuacionCrear= new Puntuacion(puntuacionNueva.getAciertos(),puntuacionNueva.getFallos(),puntuacionNueva.getTiempo(),puntuacion,servicioPuntuacion.obtenerRango(puntuacion));
        puntuacionCrear.setId(id);
        puntuacionCrear.setUsuarioPuntuacion(usu);
        puntuacionCrear.setMapaPuntuacion(map);
        servicioPuntuacion.guardar(puntuacionCrear);

        return ResponseEntity.status(HttpStatus.OK).body("Creado con éxito");
    }

    @DeleteMapping("/{idMapa}/{idUsuario}")
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

        if(puntosNuevos>puntuacionVieja.getPuntuacion()){
            puntuacionVieja.setAciertos(puntuacionNueva.getAciertos());
            puntuacionVieja.setFallos(puntuacionNueva.getFallos());
            puntuacionVieja.setPuntuacion(puntosNuevos);
            puntuacionVieja.setRango(servicioPuntuacion.obtenerRango(puntosNuevos));
            if(puntuacionNueva.getTiempo()<puntuacionVieja.getTiempo())puntuacionVieja.setTiempo(puntuacionNueva.getTiempo());
            servicioPuntuacion.modificar(puntuacionVieja);
            return ResponseEntity.status(HttpStatus.OK).body("Puntuacion actualizada");
        }

        if(puntosNuevos.equals(puntuacionVieja.getPuntuacion())){
            if(puntuacionNueva.getTiempo()<puntuacionVieja.getTiempo()){
                puntuacionVieja.setTiempo(puntuacionNueva.getTiempo());
                servicioPuntuacion.modificar(puntuacionVieja);
                return ResponseEntity.status(HttpStatus.OK).body("Puntuacion actualizada");
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Puntuacion anterior fue mejor, no modificado");
    }

    @GetMapping("/{idMapa}")
    public ResponseEntity<List<DatosClasificacionDTOEnvio>> obtenerClasificacion(@PathVariable Integer idMapa) {
        if(!servicioMapa.idExiste(idMapa)) return ResponseEntity.status(HttpStatus.CONFLICT).body(new ArrayList<>());
        List<DatosClasificacionDTO>puntuaciones =servicioPuntuacion.obtenerClasificacion(idMapa);
        List<DatosClasificacionDTOEnvio>clasificacion=new ArrayList<>();
        for(DatosClasificacionDTO puntuacion : puntuaciones){
            clasificacion.add(new DatosClasificacionDTOEnvio(puntuacion.getNombreUsuario(),
                    puntuacion.getPuntuacion(),
                    servicioPuntuacion.formatoTiempo(puntuacion.getTiempo())
                   ));
        }
        return ResponseEntity.status(HttpStatus.OK).body(clasificacion);
    }

    @GetMapping("/{idMapa}/{idUsuario}")
    public ResponseEntity<PuntuacionPersonalDTOEnvio> datosMapaUsuario(@PathVariable Integer idMapa, @PathVariable Integer idUsuario) {
        if(!servicioPuntuacion.puntuacionExiste(idMapa,idUsuario)) return ResponseEntity.status(HttpStatus.CONFLICT).body(new PuntuacionPersonalDTOEnvio());
        PuntuacionPersonalDTO puntuacion =servicioPuntuacion.obtenerPuntuacionPersonal(idMapa,idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(new PuntuacionPersonalDTOEnvio(servicioPuntuacion.obtenerTotal(puntuacion.getAciertos(),puntuacion.getFallos()),
                servicioPuntuacion.formatoTiempo(puntuacion.getTiempo()),
                puntuacion.getRango()));
    }

    @GetMapping("/puntuaciones/{nombreUsuario}")
    public ResponseEntity<List<PuntuacionPersonalPorTipoEnvio>> datosMapasUsuario(@PathVariable String nombreUsuario) {
        if(!servicioUsuario.nombreExiste(nombreUsuario)) return ResponseEntity.status(HttpStatus.CONFLICT).body(new ArrayList<>());
        List<PuntuacionPersonalPorTipo>puntuaciones =servicioPuntuacion.obtenerPuntuacionesPersonales(nombreUsuario);
        List<PuntuacionPersonalPorTipoEnvio>puntuacionPersonalPorTipoEnvios=new ArrayList<>();
        for(PuntuacionPersonalPorTipo puntuacion : puntuaciones){
            puntuacionPersonalPorTipoEnvios.add(new PuntuacionPersonalPorTipoEnvio(puntuacion.getNombreMapa(),
                    puntuacion.getTipoMapa(),
                    servicioPuntuacion.obtenerTotal(puntuacion.getAciertos(),puntuacion.getFallos()),
                    servicioPuntuacion.formatoTiempo(puntuacion.getTiempo()),
                    puntuacion.getRango()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(puntuacionPersonalPorTipoEnvios);

    }
}
