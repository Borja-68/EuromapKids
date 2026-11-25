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
import com.example.euromapapp.componentes.DatosBotonDoble
import com.example.euromapapp.componentes.DatosTextField
import com.example.euromapapp.viewModel.vm.PaginaInicioSesionVM

/**
 * Pagina para entrar en tu cuenta ya creada
 */
@Composable
fun PaginaInicioSesion(
    paginaLoginUi : PaginaInicioSesionVM = viewModel(),
    onItemClick: () -> Unit={},
    onSignUpClick:()->Unit={},
) {
    val uiState by paginaLoginUi.uiState.collectAsState()
    Box(
        Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 50.dp),
            modifier = Modifier.padding(vertical = 130.dp, horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Column(verticalArrangement = Arrangement.spacedBy(space = 35.dp)) {
                ComponenteTextField(
                    DatosTextField(
                        msjPregunta = stringResource(R.string.mensaje_usuario_log_in),
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
                        stringResource(R.string.mensaje_contrasena),
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
                        msjBot1 = stringResource(R.string.app_login),
                        msjBot2 = stringResource(R.string.app_sign_up),
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

