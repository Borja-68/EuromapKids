package com.example.euromapapp.vista.paginas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.euromapapp.R
import com.example.euromapapp.componentes.BotonesAceptarDenegarLinea
import com.example.euromapapp.componentes.BotonesDobleAvanzarLineaImagen
import com.example.euromapapp.componentes.BotonesDobleAvanzarLineaTexto
import com.example.euromapapp.componentes.ComponenteSwitchConTexto
import com.example.euromapapp.componentes.ComponenteTextField
import com.example.euromapapp.componentes.ComponenteTextFieldContrasena
import com.example.euromapapp.componentes.DatosBotonDoble
import com.example.euromapapp.componentes.DatosBotonDoblePasaImagen
import com.example.euromapapp.componentes.DatosBotonDoblePasaTexto
import com.example.euromapapp.componentes.DatosSwitch
import com.example.euromapapp.componentes.DatosTextField
import com.example.euromapapp.viewModel.vm.PaginaCambioDatosVM

var imagenesCambio: List<Int> =listOf(R.drawable.avatar_picaxu,R.drawable.ic_launcher_background)
var idiomasCambio: List<String> =listOf("es","en","la")

/**
 * Pagina para crear una nueva cuenta
 */
@Composable
fun PaginaCambioDatos(
    paginaCambiaDatosUi : PaginaCambioDatosVM = viewModel(),
    onItemClick: () -> Unit={},
    onSignUpClick:()-> Unit={}
) {
//    val uiState by paginaSignUi.uiState.collectAsState()
    var posicionImagen by remember { mutableIntStateOf(0) }
    var posicionIdioma by remember { mutableIntStateOf(0) }
    var modoOscuro by remember { mutableStateOf(true) }
    val uiState by paginaCambiaDatosUi.uiState.collectAsState()

    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 24.dp),
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            ) {
                BotonesAceptarDenegarLinea(DatosBotonDoble(
                    "Salir", "Guardar",
                    accionBoton1 = onItemClick,
                    accionBoton2 = onSignUpClick
                ))
            }
           



            Column(verticalArrangement = Arrangement.spacedBy(space = 16.dp)) {
                ComponenteTextField(
                    DatosTextField(
                        msjPregunta = stringResource(R.string.mensaje_nombre_usuario_sign_up_es),
                        listaColor = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.onBackground,
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.onPrimary
                        ),
                        txtContenido = uiState.nombre,
                        accion = {
                            paginaCambiaDatosUi.setNombre(it)
                        }
                    )
                )
                ComponenteTextFieldContrasena(
                    DatosTextField(
                        stringResource(R.string.mensaje_contrasena_usuario_sign_up_es),
                        listaColor = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.onBackground,
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.onPrimary
                        ),
                        txtContenido = uiState.contrasena,
                        accion = {
                            paginaCambiaDatosUi.setContrasena(it)
                        }
                    ),
                    onclickIcon = { paginaCambiaDatosUi.setVisibilidad() },
                    visible = uiState.contrasenaVisible
                )

                BotonesDobleAvanzarLineaTexto(datosBotones = DatosBotonDoblePasaTexto(
                    msjBot1 = "<-",
                    msjBot2 = "->",
                    texto = uiState.idiomas.get(posicionIdioma),
                    accionBoton1 = {
                        posicionIdioma = paginaCambiaDatosUi.idiomaAnterior(posicionIdioma)
                    },
                    accionBoton2 = {
                        posicionIdioma = paginaCambiaDatosUi.idiomaSiguiente(posicionIdioma)
                    }
                ))

                BotonesDobleAvanzarLineaImagen(datosBotones = DatosBotonDoblePasaImagen(
                    msjBot1 = "<-",
                    msjBot2 = "->",
                    imagen = uiState.imagenPerfil.get(posicionImagen),
                    accionBoton1 = {
                        posicionImagen = paginaCambiaDatosUi.imagenAnterior(posicionImagen)
                    },
                    accionBoton2 = {
                        posicionImagen = paginaCambiaDatosUi.imagenSiguiente(posicionImagen)
                    }
                ))

                ComponenteSwitchConTexto(valor = DatosSwitch(
                    valor = uiState.modoOscuro,
                    accion = {paginaCambiaDatosUi.setModoOscuro()}),
                    msg = "Modo Oscuro")
            }

        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PaginaCambioDatos() {
        PaginaCambioDatos(onItemClick = {}, onSignUpClick = {})
}
