package com.APIeuro.APIeuro.Controladores;

import com.APIeuro.APIeuro.DTO.CreaMapaDTO;
import com.APIeuro.APIeuro.DTO.CreaNombreMapaDTO;
import com.APIeuro.APIeuro.DTO.TipoMapaDTO;
import com.APIeuro.APIeuro.Entidades.Mapa;
import com.APIeuro.APIeuro.Entidades.NombreMapa;
import com.APIeuro.APIeuro.Entidades.NombreMapaPK;
import com.APIeuro.APIeuro.Servicios.ServicioMapa;
import com.APIeuro.APIeuro.Servicios.ServicioMapaNombre;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/euroAPI/nombreMapa")
@RequiredArgsConstructor
public class ControladorNombreMapa {
    private final ServicioMapa servicioMapa;
    private final ServicioMapaNombre servicioMapaNombre;


    @PostMapping
    public ResponseEntity<String> crearNombreMapa(@RequestBody CreaNombreMapaDTO mapaNombreNuevo) {
        if(!servicioMapa.idExiste(mapaNombreNuevo.getIdMapa())) return ResponseEntity.status(HttpStatus.CONFLICT).body("Id de mapa no existe");
        if(servicioMapaNombre.idMapaExiste(mapaNombreNuevo.getIdMapa(),mapaNombreNuevo.getIdiomaMapa())) return ResponseEntity.status(HttpStatus.CONFLICT).body("El mapa ya tiene ese idioma registrado");

        Mapa mapa=servicioMapa.obtenerMapaPorId(mapaNombreNuevo.getIdMapa());
        NombreMapaPK id= new NombreMapaPK(mapa.getIdMapa(), mapaNombreNuevo.getIdiomaMapa());
        NombreMapa nombreMapa=new NombreMapa(mapaNombreNuevo.getNombreMapa());
        nombreMapa.setId(id);
        nombreMapa.setMapa(mapa);
        servicioMapaNombre.guardar(nombreMapa);
        return ResponseEntity.status(HttpStatus.OK).body("Creado con éxito");
    }

    @DeleteMapping("/{id}-{idioma}")
    public ResponseEntity<String> borrarNombreMapa(@PathVariable Integer id,@PathVariable String idioma) {
        if(!servicioMapaNombre.idMapaExiste(id,idioma)) return ResponseEntity.status(HttpStatus.CONFLICT).body("El mapa no tiene ese idioma registrado o no está registrado el mapa");

        servicioMapaNombre.remove(new NombreMapaPK(id,idioma));
        return ResponseEntity.status(HttpStatus.OK).body("Nombre de mapa eliminado");
    }

    @PatchMapping
    public ResponseEntity<String> modificarNombreMapa(@RequestBody CreaNombreMapaDTO nombreMapaNuevo) {
        if(!servicioMapaNombre.idMapaExiste(nombreMapaNuevo.getIdMapa(), nombreMapaNuevo.getIdiomaMapa())) return ResponseEntity.status(HttpStatus.CONFLICT).body("El mapa no tiene ese idioma registrado o no está registrado el mapa");

        NombreMapa nombreMapaViejo=servicioMapaNombre.obtenerNombreMapa(nombreMapaNuevo.getIdMapa(), nombreMapaNuevo.getIdiomaMapa());
        nombreMapaViejo.setNombreMapa(nombreMapaNuevo.getNombreMapa());
        servicioMapaNombre.modificar(nombreMapaViejo);

        return ResponseEntity.status(HttpStatus.OK).body("Modificado con éxito");
    }

    @GetMapping("/{id}-{idioma}")
    public ResponseEntity<String> obtenerMapasTipo(@PathVariable Integer id,@PathVariable String idioma) {
        if(!servicioMapaNombre.idMapaExiste(id, idioma)) return ResponseEntity.status(HttpStatus.CONFLICT).body("El mapa no tiene ese idioma registrado o no está registrado el mapa");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(servicioMapaNombre.obtenerNombreDelMapa(id,idioma));
    }
}
