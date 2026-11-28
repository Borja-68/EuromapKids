package com.example.euromapapp.vista.paginas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.euromapapp.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.euromapapp.componentes.BotonesDobleAceptarColumna
import com.example.euromapapp.componentes.ComponenteTextField
import com.example.euromapapp.componentes.ComponenteTextFieldContrasena
import com.example.euromapapp.componentes.ComponenteTituloSinFondo
import com.example.euromapapp.componentes.DatosBotonDoble
import com.example.euromapapp.componentes.DatosTextField
import com.example.euromapapp.viewModel.vm.PaginaInicioSesionVM

/**
 * Pagina para entrar en tu cuenta ya creada
 */
@Composable
fun PaginaInicioSesion(
    idioma: String="es",
    paginaLoginUi : PaginaInicioSesionVM = viewModel(),
    onItemClick: () -> Unit={},
    onSignUpClick:()->Unit={},
) {
    val uiState by paginaLoginUi.uiState.collectAsState()
    Box(
        Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 50.dp),
            modifier = Modifier.padding(vertical = 130.dp, horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Column(verticalArrangement = Arrangement.spacedBy(space = 15.dp))
            {
                ComponenteTituloSinFondo(
                    titulo = stringResource(R.string.app_name),
                    tamanio = 40,
                    color = MaterialTheme.colorScheme.primary
                )

                ComponenteTituloSinFondo(
                    titulo = paginaLoginUi.obtenerString(
                        "submensaje_app_name",
                        idioma = idioma
                    ),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(space = 35.dp)) {
                ComponenteTextField(
                    DatosTextField(
                        msjPregunta = paginaLoginUi.obtenerString("mensaje_usuario_log_in",idioma),
                        listaColor = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.onBackground,
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.onPrimary
                        ),
                        txtContenido = uiState.nombreOCorreo,
                        accion = {
                            paginaLoginUi.setNombre(it)
                        }
                    )
                )
                ComponenteTextFieldContrasena(
                    DatosTextField(
                        msjPregunta = paginaLoginUi.obtenerString("mensaje_contrasena_usuario",idioma),
                        listaColor = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.onBackground,
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.onPrimary),
                        txtContenido = uiState.contrasena,
                        accion = {
                            paginaLoginUi.setContrasena(it)

                        }
                    ),
                    onclickIcon = {paginaLoginUi.setVisibilidad()},
                    visible = uiState.contrasenaVisible
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(space = 35.dp),
            ) {
                BotonesDobleAceptarColumna(
                    DatosBotonDoble(
                        msjBot1 = paginaLoginUi.obtenerString("app_login",idioma),
                        msjBot2 = paginaLoginUi.obtenerString("app_sign_up",idioma),
                        modifierBotones = Modifier.fillMaxWidth(),
                        accionBoton1 = onItemClick,
                        accionBoton2 = onSignUpClick
                    )
                )
            }
        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun PrevPaginaLogin() {
        PaginaInicioSesion(
            onItemClick = {},
            onSignUpClick = {}
        )
}

