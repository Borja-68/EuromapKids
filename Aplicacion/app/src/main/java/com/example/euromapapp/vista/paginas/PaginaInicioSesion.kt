package com.example.euromapapp.vista.paginas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.euromapapp.R
import com.example.euromapapp.componentes.BotonesDobleAceptarColumna
import com.example.euromapapp.componentes.ComponenteTextField
import com.example.euromapapp.componentes.ComponenteTextFieldContrasena
import com.example.euromapapp.componentes.DatosBotonDoble
import com.example.euromapapp.componentes.DatosTextField

/**
 * Pagina para entrar en tu cuenta ya creada
 */
@Composable
fun PaginaLogin(
    onItemClick: () -> Unit,
    onSignUpClick:()->Unit,
) {
    val contrasenaVisible: MutableState<Boolean> =remember { mutableStateOf(value = true) }
    val cadUsuario: MutableState<String> = remember { mutableStateOf(value = "as") }
    val cadContrasena: MutableState<String> = remember { mutableStateOf(value = "as") }
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
                        txtContenido = cadUsuario.value,
                        accion = {
                            cadUsuario.value=it
                            return@DatosTextField cadUsuario.value
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
                        txtContenido = cadContrasena.value,
                        accion = {
                            cadContrasena.value=it
                            return@DatosTextField cadContrasena.value
                        }
                    ),
                    onclickIcon = {contrasenaVisible.value=!contrasenaVisible.value},
                    visible = contrasenaVisible.value
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
                        accionBoton1 =
                            {
//                                paginaLoginUi.logIn(
//                                    onSucces = onItemClick,
//                                    onError = {}
//                                )
                            },
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
        PaginaLogin(onItemClick = {}, onSignUpClick = {})
}
