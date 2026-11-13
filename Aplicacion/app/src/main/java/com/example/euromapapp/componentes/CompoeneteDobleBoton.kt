package com.example.euromapapp.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.euromapapp.R

val divisorTamamioImagen=2

/**
 * @param msjBot1 mensaje que aparecera dentro del primer boton
 * @param msjBot2 mensaje que aparecera dentro del segundo boton
 * @param modifierBotones modificador que se puede usar para cambiar el modificador de los botones llamados
 * @param tamanioTexto Tamaño del texto de dentro de los botones
 * @param accionBoton1 acccion que tomara el primer boton al hacerle click
 * @param accionBoton2 acccion que tomara el primer boton al hacerle click
 */
class DatosBotonDoble(
    var msjBot1: String,
    var msjBot2: String,
    var modifierBotones: Modifier= Modifier,
    var tamanioTexto:Int=18,
    var accionBoton1: () -> Unit = {},
    var accionBoton2: () -> Unit = {}
)

class DatosBotonDoblePasaTexto(
    var msjBot1: String,
    var msjBot2: String,
    var texto: String,
    var modifierBotones: Modifier= Modifier,
    var tamanioTexto:Int=18,
    var accionBoton1: () -> Unit = {},
    var accionBoton2: () -> Unit = {}
)
class DatosBotonDoblePasaImagen(
    var msjBot1: String,
    var msjBot2: String,
    var imagen: Int,
    var tamanioImagen: Int=60,
    var modifierBotones: Modifier= Modifier,
    var accionBoton1: () -> Unit = {},
    var accionBoton2: () -> Unit = {}
)



/**
 * creación en linea de dos botones, uno para denegar y otro para aceptar,
 * tendrán el mismo tamaño los dos en base al espacio disponible
 */
@Composable
fun BotonesAceptarDenegarLinea(datosBotones: DatosBotonDoble) {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 3.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(30.dp),
    ) {

        Box(modifier = Modifier.weight(1f)) {

            DenegarBoton(
                msj = datosBotones.msjBot1,
                modifier = Modifier.fillMaxWidth(),
                tamanioTexto = datosBotones.tamanioTexto,
                accion = datosBotones.accionBoton1
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            AvanzarBoton(
                msj = datosBotones.msjBot2,
                modifier = Modifier.fillMaxWidth(),
                tamanioTexto = datosBotones.tamanioTexto,
                accion = datosBotones.accionBoton2


            )
        }
    }
}



/**
 * creación en linea de dos botones,
 * tendrán el mismo tamaño los dos en base al espacio disponible
 */
@Composable
fun BotonesDobleAceptarLinea(datosBotones: DatosBotonDoble) {
    Row(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            AceptarBoton(
                datosBotones.msjBot1, Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                tamanioTexto = datosBotones.tamanioTexto,
                accion = datosBotones.accionBoton1
            )
        }
        Box(
            modifier = Modifier.weight(1f)
        ) {
            AceptarBoton(
                datosBotones.msjBot2, Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                tamanioTexto = datosBotones.tamanioTexto,
                accion = datosBotones.accionBoton2
            )
        }
    }
}

/**
 * creación en linea de dos botones con un texto en el centro,
 * tendrán el mismo tamaño los dos en base al espacio disponible
 */
@Composable
fun BotonesDobleAvanzarLineaTexto(datosBotones: DatosBotonDoblePasaTexto) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier.weight(0.5f)
        ) {
            AvanzarBoton(
                datosBotones.msjBot1, Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                tamanioTexto = datosBotones.tamanioTexto,
                accion = datosBotones.accionBoton1
            )
        }
        Box(  modifier = Modifier.weight(1f)){
            ComponenteTitulo(datosBotones.texto,datosBotones.tamanioTexto)
        }
        Box(
            modifier = Modifier.weight(0.5f)
        ) {
            AvanzarBoton(
                datosBotones.msjBot2, Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                tamanioTexto = datosBotones.tamanioTexto,
                accion = datosBotones.accionBoton2
            )
        }
    }
}

/**
 * creación en linea de dos botones con un texto en el centro,
 * tendrán el mismo tamaño los dos en base al espacio disponible
 */
@Composable
fun BotonesDobleAvanzarLineaImagen(datosBotones: DatosBotonDoblePasaImagen) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier.weight(0.75f)
        ) {
            AvanzarBoton(
                datosBotones.msjBot1, Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                tamanioTexto = (datosBotones.tamanioImagen/divisorTamamioImagen),
                accion = datosBotones.accionBoton1
            )
        }
        Box(  modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center){
            ComponenteImagenRedondeadaLocal(datosBotones.imagen, tamanio = datosBotones.tamanioImagen)
        }
        Box(
            modifier = Modifier.weight(0.75f)
        ) {
            AvanzarBoton(
                datosBotones.msjBot2, Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                tamanioTexto = (datosBotones.tamanioImagen/divisorTamamioImagen),
                accion = datosBotones.accionBoton2
            )
        }
    }
}

/**
 * creación en columna de dos botones,
 * tendrán el mismo tamaño los dos en base al espacio disponible
 */
@Composable
fun BotonesDobleAceptarColumna(datosBotones: DatosBotonDoble) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 40.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        AceptarBoton(msj = datosBotones.msjBot1,
            modifier = datosBotones.modifierBotones,
            tamanioTexto = datosBotones.tamanioTexto,
            accion = datosBotones.accionBoton1)
        AceptarBoton(msj = datosBotones.msjBot2,
            modifier = datosBotones.modifierBotones,
            tamanioTexto = datosBotones.tamanioTexto,
            accion = datosBotones.accionBoton2)
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewDobleBoton() {
    Column {
        var botones= DatosBotonDoble("Salir","Guardar")
        var botonesAvanzar = DatosBotonDoble("<-","->")
        var botonesAvanzarTexto = DatosBotonDoblePasaTexto("<-","->","es")
        var botonesAvanzarImagen = DatosBotonDoblePasaImagen("<-","->", imagen = R.drawable.picaxu , tamanioImagen = 70)
        BotonesAceptarDenegarLinea(botones)
        BotonesDobleAceptarLinea(botones)
        BotonesDobleAceptarColumna(botones)
        BotonesDobleAvanzarLineaTexto(datosBotones = botonesAvanzarTexto)
        BotonesDobleAvanzarLineaImagen(botonesAvanzarImagen)
    }
}
