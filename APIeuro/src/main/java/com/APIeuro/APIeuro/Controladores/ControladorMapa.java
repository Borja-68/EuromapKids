package com.APIeuro.APIeuro.Controladores;
import com.APIeuro.APIeuro.DTO.*;
import com.APIeuro.APIeuro.Entidades.Mapa;
import com.APIeuro.APIeuro.Entidades.Usuario;
import com.APIeuro.APIeuro.Servicios.ServicioMapa;
import com.APIeuro.APIeuro.Servicios.ServicioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/euroAPI/mapa")
@RequiredArgsConstructor
public class ControladorMapa {
    private final ServicioMapa servicioMapa;

    @PostMapping
    public ResponseEntity<String> crearMapa(@RequestBody CreaMapaDTO mapaNuevo) {
        servicioMapa.guardar(new Mapa(mapaNuevo.getTipoMapa(),mapaNuevo.getImagen()));
        return ResponseEntity.status(HttpStatus.OK).body("Creado con éxito");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarMapa(@PathVariable Integer id) {
        if(!servicioMapa.idExiste(id)) return ResponseEntity.status(HttpStatus.CONFLICT).body("Id de mapa no existe");
        servicioMapa.remove(id);
        return ResponseEntity.status(HttpStatus.OK).body("mapa eliminado");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> modificarMapa(@PathVariable Integer id,@RequestBody CreaMapaDTO mapaNuevo) {
        if(!servicioMapa.idExiste(id)) return ResponseEntity.status(HttpStatus.CONFLICT).body("Id de mapa no existe");
        Mapa mapaAModificar=servicioMapa.obtenerMapaPorId(id);

        if(mapaNuevo.getTipoMapa()!=null)mapaAModificar.setTipoMapa(mapaNuevo.getTipoMapa());
        if(mapaNuevo.getImagen()!=null)mapaAModificar.setImagen(mapaNuevo.getImagen());

        servicioMapa.modificar(mapaAModificar);
        return ResponseEntity.status(HttpStatus.OK).body("Modificado con éxito");
    }

    @GetMapping("/{tipoMapa}")
    public ResponseEntity<List<TipoMapaDTO>> obtenerMapasTipo(@PathVariable String tipoMapa) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(servicioMapa.obtenerMapasPorTipo(tipoMapa));
    }
}
