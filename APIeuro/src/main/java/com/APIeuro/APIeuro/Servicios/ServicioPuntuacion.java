package com.APIeuro.APIeuro.Servicios;

import com.APIeuro.APIeuro.DTO.DatosClasificacionDTO;
import com.APIeuro.APIeuro.DTO.PuntuacionPersonalDTO;
import com.APIeuro.APIeuro.Entidades.NombreMapa;
import com.APIeuro.APIeuro.Entidades.NombreMapaPK;
import com.APIeuro.APIeuro.Entidades.Puntuacion;
import com.APIeuro.APIeuro.Entidades.PuntuacionPK;
import com.APIeuro.APIeuro.Repositorios.MapaNombreRepository;
import com.APIeuro.APIeuro.Repositorios.PuntuacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sound.midi.MidiUnavailableException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioPuntuacion {

    private final PuntuacionRepository repoPuntuacion;
    private final Integer puntosTotales=1000;

    public void guardar(Puntuacion puntuacion) {
        repoPuntuacion.save(puntuacion);
    }
    public Puntuacion modificar(Puntuacion puntuacion) { return repoPuntuacion.save(puntuacion);}
    public void remove(PuntuacionPK id){
        repoPuntuacion.deleteById(id);
    }

    public Integer calcularPuntuacion(Integer aciertos,Integer fallos){
        Integer puntosPorAcierto= puntosTotales/(aciertos+fallos);
        return puntosPorAcierto*aciertos;
    }

    public char obtenerRango(Integer puntuacion){
        char rango='F';
        if(puntuacion>=0 && puntuacion<(puntosTotales*0.25))rango='F';
        if(puntuacion>(puntosTotales*0.25) && puntuacion<(puntosTotales*0.5))rango='D';
        if(puntuacion>=(puntosTotales*0.5) && puntuacion<(puntosTotales*0.6))rango='C';
        if(puntuacion>=(puntosTotales*0.6) && puntuacion<(puntosTotales*0.75))rango='B';
        if(puntuacion>=(puntosTotales*0.75) && puntuacion<(puntosTotales*0.9))rango='A';
        if(puntuacion>=(puntosTotales*0.9))rango='S';
        return rango;
    }

    public boolean puntuacionExiste(Integer idMapa, Integer idUsuario){
        return repoPuntuacion.puntuacionExiste(idMapa, idUsuario)!=null;
    }

    public Puntuacion obtenerPuntuacion(Integer idMapa, Integer idUsuario){
        return repoPuntuacion.obtenerPuntuacionExiste(idMapa, idUsuario);
    }

    public List<DatosClasificacionDTO> obtenerClasificacion(Integer idMapa){
        return repoPuntuacion.listarPuntuacionesMapa(idMapa);
    }

    public PuntuacionPersonalDTO obtenerPuntuacionPersonal(Integer idMapa, Integer idUsuario){
        return repoPuntuacion.obtenerPuntuacionPersonal(idMapa,idUsuario);
    }

}
