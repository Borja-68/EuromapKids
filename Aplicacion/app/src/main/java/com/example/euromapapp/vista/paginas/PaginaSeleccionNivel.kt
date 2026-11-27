package com.example.euromapapp.vista.paginas

import ComponenteTarjetaPuntuacion
import TarjetaResultado
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.euromapapp.componentes.BotonesClasificacionJugar
import com.example.euromapapp.componentes.BotonesDobleAvanzarNivel
import com.example.euromapapp.componentes.ComponenteImagenLocal
import com.example.euromapapp.componentes.ComponenteImagenMapaSeleccion
import com.example.euromapapp.componentes.ComponenteTitulo
import com.example.euromapapp.componentes.ComponenteTituloCaja
import com.example.euromapapp.componentes.DatosBotonDobleClasificacionJugar
import com.example.euromapapp.componentes.DatosBotonDoblePasaNivel
import com.example.euromapapp.componentes.DatosComponenteImagenMapaSeleccion
import com.example.euromapapp.componentes.DatosTituloCaja
import com.example.euromapapp.componentes.DenegarBoton
import com.example.euromapapp.viewModel.vm.PaginaSeleccionNivelVM

val tamanioTextoNombreMapaSeleccion=40
val tamanioImagenMapaSeleccion=400
val tamanioPuntuacionMapaSeleccion=50
val tamanioMirarClasificacionSeleccion=20


@Composable
fun PaginaSeleccionNivel(
    idioma: String="",
    tipo: String="",
    idMapa: Int =0,
    paginaSeleccionNivelUi : PaginaSeleccionNivelVM = viewModel(),
    accionClickImagen: (Int,String) -> Unit={_, _ ->},
    onClickClasificacion: (Int,String)-> Unit={_, _ ->}
) {
    val uiState by paginaSeleccionNivelUi.uiState.collectAsState()
    var posicionMapa by remember { mutableIntStateOf(paginaSeleccionNivelUi.obtenerPosicionMapaId(mapaId = idMapa))  }

    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 40.dp),
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            ComponenteImagenMapaSeleccion(
                datos = DatosComponenteImagenMapaSeleccion(
                    modifier = Modifier,
                    id = uiState.mapas.get(posicionMapa).imagenMapa,
                    nombre = uiState.nombreMapa,
                    total = uiState.mapas.size,
                    posicion = posicionMapa,
                    onAnterior = {
                        posicionMapa = paginaSeleccionNivelUi.mapaAnterior(posicionMapa)
                    },
                    onSiguiente = {
                        posicionMapa= paginaSeleccionNivelUi.mapaSiguiente(posicionMapa)
                    }
                )
            )


            ComponenteTarjetaPuntuacion(
                contenido = TarjetaResultado(
                    msjArriba = paginaSeleccionNivelUi.obtenerString("msj_puntuacion",idioma),
                    msjAciertos = paginaSeleccionNivelUi.obtenerString("msj_aciertos",idioma),
                    msjTiempo = paginaSeleccionNivelUi.obtenerString("msj_tiempo",idioma),
                    imagen = paginaSeleccionNivelUi.drawableParaRango(uiState.puntuacion.rango),
                    tiempo = uiState.puntuacion.tiempo,
                    aciertos = uiState.puntuacion.aciertos
                ),
                tamanio = tamanioPuntuacionMapaSeleccion)

                BotonesClasificacionJugar(
                    datos = DatosBotonDobleClasificacionJugar(
                        modifier = Modifier,
                        msjJugar = paginaSeleccionNivelUi.obtenerString("boton_juego", idioma),
                        accionJugar = {
                            accionClickImagen(
                                uiState.mapas.get(posicionMapa).idMapa,
                                tipo
                            )
                        },
                        msjClasificacion = paginaSeleccionNivelUi.obtenerString(
                            "titulo_clasificacion",
                            idioma
                        ),
                        accionClasificacion = {
                            onClickClasificacion(
                                uiState.mapas.get(posicionMapa).idMapa,
                                tipo
                            )
                        },
                        habilitado = true
                    )

                )

        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun PrevPaginaSeleccionNivel() {
    PaginaSeleccionNivel()
}
