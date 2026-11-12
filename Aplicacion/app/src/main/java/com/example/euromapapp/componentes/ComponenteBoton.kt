package com.example.euromapapp.componentes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * @param msj texto que tendrá el boton
 * @param modifier modificador del boton, es opcional
 *
 * @param coloresBoton lista de 4,
 * se utiliza para determinar los colores del boton en orden: contenedor, contenido,
 * contenedor en inactivo y contenido en inactivo
 *
 * @param accion accion del boton al hacer click
 *
 * @param tamanioTexto tamaño de el texto que contiene el boton
 */
class DatosBoton(
    val msj: String,
    val modifier: Modifier = Modifier,
    val coloresBoton: List<Color>,
    val tamanioTexto:Int=12,
    val accion: () -> Unit
)



@Composable
fun ComponenteBoton(datos: DatosBoton) {
    Button(
        onClick = datos.accion,
        colors = ButtonColors(
            containerColor = datos.coloresBoton.get(0),
            contentColor = datos.coloresBoton.get(1),
            disabledContainerColor = datos.coloresBoton.get(2),
            disabledContentColor = datos.coloresBoton.get(3),
        ),
        modifier = datos.modifier
    ) {
        Text(text = datos.msj,
            fontSize = datos.tamanioTexto.sp)
    }
}


/**
 * crea un boton de color de fondo claro
 */
@Composable
fun AceptarBoton(
    msj: String,
    modifier: Modifier = Modifier,
    tamanioTexto: Int=16,
    accion: () -> Unit = { Log.e("Testing", "Aceptar boton cliqueado") }
) {
    var listaColor = listOf<Color>(
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.onSecondary,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.onPrimary
    )
    ComponenteBoton(
        datos = DatosBoton(
            msj = msj,
            modifier = modifier,
            coloresBoton = listaColor,
            tamanioTexto = tamanioTexto,
            accion = accion
        )
    )
}

/**
 * crea un boton de color de fondo oscuro
 */
@Composable
fun DenegarBoton(
    msj: String,
    modifier: Modifier = Modifier,
    tamanioTexto: Int=18,
    accion: () -> Unit = { Log.e("Testing", "Denegar boton cliqueado") }
) {
    var listaColor = listOf<Color>(
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.onError,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.onSecondary
    )
    ComponenteBoton(
        datos = DatosBoton(
            msj = msj,
            modifier = modifier,
            coloresBoton = listaColor,
            tamanioTexto = tamanioTexto,
            accion = accion
        )
    )
}

/**
 * crea un boton de color de fondo oscuro
 */
@Composable
fun AvanzarBoton(
    msj: String,
    modifier: Modifier = Modifier,
    tamanioTexto: Int=18,
    accion: () -> Unit = {}
) {
    var listaColor = listOf<Color>(
        MaterialTheme.colorScheme.onBackground,
        MaterialTheme.colorScheme.background,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.onSecondary
    )
    ComponenteBoton(
        datos = DatosBoton(
            msj = msj,
            modifier = modifier,
            coloresBoton = listaColor,
            tamanioTexto = tamanioTexto,
            accion = accion
        )
    )
}

@Preview
@Composable
fun BotonPreview() {
    Column(Modifier.fillMaxSize()) {
        AceptarBoton("Guardar")
        DenegarBoton("Salir")
        AvanzarBoton("Siguiente")
    }
}