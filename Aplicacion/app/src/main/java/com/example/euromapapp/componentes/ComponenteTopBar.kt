package com.example.euromapapp.componentes

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
    accionMenu: () -> Unit={ },
    accionCerrarSesion:()->Unit={ Log.e("Testeo","inicio sesion cliqueada")},
    accionCamara: () -> Unit={ Log.e("Testeo","camara cliqueada")},
    accionPerfil: () -> Unit={Log.e("Testeo","perfil cliqueada")}
) {
    TopAppBar(
        title = { Text(text = title, textAlign = TextAlign.Center) },
        navigationIcon = {
            IconButton(onClick = accionMenu) {
                Icon(Icons.Default.Menu, contentDescription = stringResource(R.string.app_name))
            }
        },
        actions = {
                IconButton(onClick = accionCerrarSesion) {
                    Icon(
                        Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = stringResource(R.string.app_name)
                    )
                }

//            IconButton(onClick = accionCamara) {
//                Icon(Icons.Default.Create, contentDescription = stringResource(R.string.app_toolbar_Camara))
//            }
            IconButton(onClick = accionPerfil) {
                Icon(Icons.Default.AccountCircle, contentDescription = stringResource(R.string.app_name))
            }
        }
    )
}

@Preview
@Composable
fun PreviewTop(){
    ComponenteTopBar("Página 1",{},{},{})
}
