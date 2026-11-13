import com.example.euromapapp.componentes.ComponenteImagen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.euromapapp.R
import com.example.euromapapp.componentes.ComponenteImagenLocal

/**
 *@param imagen la imagen de la tarjeta
 *@param titulo el titulo de la tarjeta
 *@param accion lo que hara cuando se haga click en la tarjeta
 */
data class Tarjeta(
    val imagen: String="",
    val titulo: String="",
    val accion: () -> Unit = { Log.e("Testeo","tarjeta cliqueada")},
)

data class TarjetaResultadoPerfil(
    val imagen: Int,
    val tipo: String="",
    val nombreMapa: String="",
    val aciertos: String="",
    val tiempo: String="",
    val accion: () -> Unit = { Log.e("Testeo","tarjeta cliqueada")},
)


data class TarjetaResultado(
    val imagen: Int,
    val aciertos: String="",
    val tiempo: String="",
    val accion: () -> Unit = { Log.e("Testeo","tarjeta cliqueada")},
)

/**
 * crea una tarjeta con el contenido en horizontal
 * @param tamanio cambia el tamaño de la imagen y texto de la tarjeta, predefinido a 40
 */
@Composable
fun ComponenteTarjetaHorizontalPersonal(
    contenido: TarjetaResultadoPerfil,
    tamanio: Int = 40
) {
    var cadena=
            "    Aciertos: "+contenido.aciertos+
            "    Tiempo: "+contenido.tiempo+
            "    Nombre: "+contenido.nombreMapa+
            "    Nombre: "+contenido.tipo
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .height(height = (tamanio * 1.25).dp)
            .fillMaxWidth()
    ) {
        ComponenteImagenLocal(id = contenido.imagen, tamanio = tamanio)
        Text(
            text = cadena,
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
            fontSize = (tamanio * 0.5).sp,
            color = MaterialTheme.colorScheme.onSecondary,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3
        )
    }
}

@Composable
fun ComponenteTarjetaPuntuacion(
    contenido: TarjetaResultado,
    tamanio: Int = 40
) {
    var cadena="  Aciertos: "+contenido.aciertos+"    Tiempo: "+contenido.tiempo
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .height(height = (tamanio * 1.25).dp)
            .fillMaxWidth()
    ) {
        ComponenteImagenLocal(id = contenido.imagen, tamanio = tamanio)
        Text(
            text = cadena,
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
            fontSize = (tamanio * 0.5).sp,
            color = MaterialTheme.colorScheme.onSecondary,
            overflow = TextOverflow.Visible,
            maxLines = 3
        )
    }
}

///**
//* crea una tarjeta con el contenido en vertical
//* @param tamanio cambia el tamaño de la imagen y texto de la tarjeta, predefinido a 40
//*/
//@Composable
//fun ComponenteTarjetaVertical(
//    contenido: Tarjeta,
//    tamanio: Int = 40
//) {
//    Box( modifier = Modifier.clickable(onClick ={contenido.accion()})
//        .background(color = MaterialTheme.colorScheme.secondary)
//        .padding(horizontal = 4.dp,)
//        .width(width = (tamanio*4).dp),
//        contentAlignment = Alignment.Center
//    ){
//        Column (
//            horizontalAlignment = Alignment.CenterHorizontally){
//            ComponenteImagen(id = contenido.imagen, tamanio = (tamanio*4))
//            Text(
//                text = contenido.titulo,
//                modifier = Modifier.padding(horizontal = 4.dp),
//                fontSize = (tamanio).sp,
//                color =MaterialTheme.colorScheme.onSecondary,
//                overflow = TextOverflow.Ellipsis,
//                maxLines = 3
//
//            )
//        }
//    }
//}
//

@Preview
@Composable
fun PrevioTarjHoriz() {
    Column {
    val contenido = TarjetaResultadoPerfil(imagen = R.drawable.picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10")
    val contenidoPuntuacion = TarjetaResultado(R.drawable.picaxu, aciertos = "7/10", tiempo = "2:40:22")
        ComponenteTarjetaHorizontalPersonal(contenido)
        ComponenteTarjetaPuntuacion(contenidoPuntuacion)
//        ComponenteTarjetaVertical(contenido)
    }
}