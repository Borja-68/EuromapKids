package com.example.euromapapp.componentes

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.euromapapp.R

/**
 * Barra de navegacion de los escafolds
 * @param title texto que estara en el centro
 * @param accionMenu acccion del menu al hacer click
 * @param accionCerrarSesion acion que se hara al hacer click en el icono de cerrar sesion
 * @param accionCamara acccion del la barra de busqueda al hacer click
 * @param accionPerfil acccion del perfil al hacer click
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponenteTopBar(
    title: String,
    accionCerrarSesion:()->Unit={ Log.e("Testeo","inicio sesion cliqueada")},
    accionPerfil: () -> Unit={Log.e("Testeo","perfil cliqueada")}
) {
    TopAppBar(
        title = { Text(text = title, textAlign = TextAlign.Center) },
        actions = {

            IconButton(onClick = accionPerfil) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = stringResource(R.string.app_name)
                )
            }
            IconButton(onClick = accionCerrarSesion) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = stringResource(R.string.app_name)

                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorFondoTopBarPagina,
            titleContentColor = colorTextoTopBarPagina,
            navigationIconContentColor = colorTextoTopBarPagina,
            actionIconContentColor = Color.Black
        )
    )
}

private val colorFondoTopBarPagina = Color(0xFFA9C8FF)
private val colorTextoTopBarPagina = Color(0xFF062449)

/** Top app bar de Material 3 para páginas secundarias. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponenteTopBarAtras(
    title: String,
    accionAtras: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = accionAtras) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorFondoTopBarPagina,
            titleContentColor = colorTextoTopBarPagina,
            navigationIconContentColor = colorTextoTopBarPagina
        )
    )
}


@Preview
@Composable
fun PreviewTop(){
    ComponenteTopBar("Página 1",{},{})
}
