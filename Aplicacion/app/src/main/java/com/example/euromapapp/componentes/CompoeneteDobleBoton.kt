package com.example.euromapapp.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.euromapapp.R

val divisorTamamioImagen=4

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

class DatosBotonDobleClasificacionJugar(
    val msjClasificacion: String,
    val msjJugar: String,
    val accionClasificacion: () -> Unit,
    val accionJugar: () -> Unit,
    val modifier: Modifier = Modifier,
    var habilitado: Boolean = true
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

class DatosBotonDoblePasaNivel(
    var msjBot1: String,
    var msjBot2: String,
    var modifierBotones: Modifier= Modifier,
    var tamanioTexto:Int=18,
    var accionBoton1: () -> Unit = {},
    var accionBoton2: () -> Unit = {}
)
class DatosBotonDoblePasaImagen(
    var msjBot1: String,
    var msjBot2: String,
    var imagen: Int,
    var tamanioImagen: Int=70,
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
            modifier = Modifier.weight(0.25f)
        ) {
            AvanzarBoton(
                datosBotones.msjBot1, Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                tamanioTexto = datosBotones.tamanioTexto,
                accion = datosBotones.accionBoton1
            )
        }
        Box(  modifier = Modifier.weight(0.5f)){
            ComponenteTituloCaja(DatosTituloCaja(datosBotones.texto,datosBotones.tamanioTexto))
        }
        Box(
            modifier = Modifier.weight(0.25f)
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
fun BotonesDobleAvanzarNivel(datosBotones: DatosBotonDoblePasaNivel) {
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
            modifier = Modifier.weight(0.25f)
        ) {
            AvanzarBoton(
                datosBotones.msjBot1, Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                tamanioTexto = (datosBotones.tamanioImagen/divisorTamamioImagen),
                accion = datosBotones.accionBoton1
            )
        }
        Box(  modifier = Modifier.weight(0.5f),
            contentAlignment = Alignment.Center){
            ComponenteImagenRedondeadaLocal(datosBotones.imagen, tamanio = datosBotones.tamanioImagen)
        }
        Box(
            modifier = Modifier.weight(0.25f)
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

/** Fila de acciones inferior para la selección de mapa. */
@Composable
fun BotonesClasificacionJugar(
    datos: DatosBotonDobleClasificacionJugar
) {
    val azulClaro = Color(0xFFA9C8FF)
    val azulOscuro = Color(0xFF062449)

    Row(
        modifier = datos.modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedButton(
            onClick = datos.accionClasificacion,
            enabled = datos.habilitado,
            modifier = Modifier.weight(1f).height(72.dp),
            shape = CircleShape,
            border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outline),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = azulClaro
            )
        ) {
            Icon(Icons.Default.BarChart, contentDescription = null)
            Text(
                text = datos.msjClasificacion,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 18.sp
            )
        }

        Button(
            onClick = datos.accionJugar,
            enabled = datos.habilitado,
            modifier = Modifier.weight(1f).height(72.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = azulClaro,
                contentColor = azulOscuro
            )
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = null)
            Text(
                text = datos.msjJugar,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 18.sp
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewDobleBoton() {
    Column {
        var botones= DatosBotonDoble("Salir","Guardar")
        var botonesAvanzar = DatosBotonDoble("<-","->")
        var botonesAvanzarTexto = DatosBotonDoblePasaTexto("<-","->","es")
        var botonesAvanzarImagen = DatosBotonDoblePasaImagen("<-","->", imagen = R.drawable.avatar_picaxu , tamanioImagen = 70)
        BotonesAceptarDenegarLinea(botones)
        BotonesDobleAceptarLinea(botones)
        BotonesDobleAceptarColumna(botones)
        BotonesDobleAvanzarLineaTexto(datosBotones = botonesAvanzarTexto)
        BotonesDobleAvanzarLineaImagen(botonesAvanzarImagen)
    }
}
