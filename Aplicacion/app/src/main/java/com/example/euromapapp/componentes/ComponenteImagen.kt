package com.example.euromapapp.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.euromapapp.R

/**
 * Crea una imagen de la id de la imagen que se le pase
 * @param id la imagen que se quiere que aparezca del api
 * @param tamanio el tamaño de la imagen, por defecto a 20
 */
@Composable
fun ComponenteImagen(
    id: String,
    tamanio: Int = 20
) {

    AsyncImage(
        model = "$id",
        modifier = Modifier
            .size(width = tamanio.dp, height = tamanio.dp)
            .padding(vertical = 2.dp, horizontal = 2.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        contentDescription = "imagen categoria"
    )

}

/**
 * Crea una imagen de la id de la imagen que se le pase
 * @param id la imagen que se quiere que aparezca del api
 * @param tamanio el tamaño de la imagen, por defecto a 20
 */
@Composable
fun ComponenteImagenLocal(
    id: Int,
    tamanio: Int = 40,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier.size(tamanio.dp),
    contentScale: ContentScale = ContentScale.Fit,
    forma: Shape = RoundedCornerShape(10.dp)
) {
    Box(modifier.clickable(onClick = onClick)) {
        Image(
            painter = painterResource(id = id),
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 2.dp, horizontal = 2.dp)
                .clip(forma),
            contentScale = contentScale,
            contentDescription = "imagen categoria"
        )
    }
}


@Composable
fun ComponenteImagenBaseDatos(
    imagen: ByteArray?,
    tamanio: Int = 40,
    accion: ()-> Unit ={}
) {
Box(Modifier.clickable(onClick = accion)){
    AsyncImage(
        model =  imagen,
        modifier = Modifier
            .size(width = tamanio.dp, height = tamanio.dp)
            .padding(vertical = 2.dp, horizontal = 2.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        contentDescription = "imagen categoria"
    )
}


}
/**
 * Crea una imagen redondeada
 * @param id la imagen que se quiere que aparezca del api
 * @param tamanio el tamaño de la imagen, por defecto a 20
 */
@Composable
fun ComponenteImagenRedondeadaLocal(
    id: Int,
    tamanio: Int = 40
) {

    Image(
        painter = painterResource(id = id),
        modifier = Modifier
            .size(width = tamanio.dp, height = tamanio.dp)
            .clip(shape = CircleShape),
        contentDescription = "imagen categoria"
    )

}

@Composable
fun ComponenteImagenRedondeadaBase(
    imagen: ByteArray?,
    tamanio: Int = 40
) {

    AsyncImage(
        model =  imagen,
        modifier = Modifier
            .size(width = tamanio.dp, height = tamanio.dp)
            .clip(shape = CircleShape),
        contentDescription = "imagen categoria"
    )

}

@Preview
@Composable
fun ImagenPreview() {
    ComponenteImagenLocal(R.drawable.avatar_picaxu)
}

@Preview
@Composable
fun ImagenPreviewRedondeada() {
    ComponenteImagenRedondeadaLocal(R.drawable.avatar_picaxu)
}
