package com.example.euromapapp.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview


/**
 * Componente para introducir textos
 * @param msjPregunta texto que aparece antes de introducir un texto
 * @param txtContenido texto introducido empleado para actualizar
 * @param listaColor colores que emplea
 * @param accion lo que hace
 */
class DatosTextField(
    val msjPregunta: String = "Introduca aquí nombre del Trivial a crear",
    var txtContenido: String = "",
    val modif: Modifier = Modifier.fillMaxWidth(),
    var listaColor: List<Color> = listOf(),
    val accion: (String) -> String = { "" }

)

/**
 * Componente para introducir textos de una lista
 * @param msjPregunta texto que aparece antes de introducir un texto
 * @param txtContenido texto introducido empleado para actualizar
 * @param i elemento de la lista al que pertenece
 * @param listaColor colores que emplea
 * @param accion lo que hace
 */
class DatosTextFieldLista(
    val msjPregunta: String = "Introduca aquí nombre del Trivial a crear",
    var txtContenido: String = "",
    val i: Int,
    val modif: Modifier = Modifier.fillMaxWidth(),
    var listaColor: List<Color> = listOf(),
    val accion: (Int, String) -> String = { i, it -> "" }

)

/**
 * crea una caja donde el usuario puede escribir
 */
@Composable
fun ComponenteTextField(datos: DatosTextField) {

    TextField(
        colors = TextFieldDefaults.colors(
            focusedContainerColor = datos.listaColor.get(0),
            unfocusedContainerColor = datos.listaColor.get(0),
            focusedIndicatorColor = datos.listaColor.get(2),
            cursorColor = datos.listaColor.get(3),
            focusedTextColor = datos.listaColor.get(1),
            unfocusedTextColor = datos.listaColor.get(1)
        ),
        modifier = datos.modif,
        value = datos.txtContenido,
        onValueChange = { txtNuevo -> datos.txtContenido = datos.accion(txtNuevo) },
        label = { Text(datos.msjPregunta, color = datos.listaColor.get(1)) },

        )
}

/**
 * crea una caja donde el usuario puede escribir, pero los datos pertenecen a una lista
 */
@Composable
fun ComponenteTextFieldLista(datos: DatosTextFieldLista) {
    datos.listaColor = listOf(
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.onSecondary,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.onPrimary
    )

    TextField(
        colors = TextFieldDefaults.colors(
            focusedContainerColor = datos.listaColor.get(0),
            unfocusedContainerColor = datos.listaColor.get(0),
            focusedIndicatorColor = datos.listaColor.get(2),
            cursorColor = datos.listaColor.get(3),
            focusedTextColor = datos.listaColor.get(1),
            unfocusedTextColor = datos.listaColor.get(1)
        ),
        modifier = datos.modif,
        value = datos.txtContenido,
        onValueChange = { txtNuevo ->
            datos.txtContenido = datos.accion(datos.i, txtNuevo)
        },
        label = { Text(datos.msjPregunta, color = datos.listaColor.get(1)) },

        )
}

/**
 * crea una caja donde el usuario puede escribir si contrasena,
 * esta contiene un icono que permita mostrar o ocultar lo escrito
 * @param visible parametro utilizado para saber si mostrar o no
 * @param onclickIcon lo que se hace al hacer click al icono
 */
@Composable
fun ComponenteTextFieldContrasena(
    datos: DatosTextField,
    visible: Boolean = false,
    onclickIcon: () -> Unit
) {
    datos.listaColor = listOf(
        MaterialTheme.colorScheme.background,
        MaterialTheme.colorScheme.onBackground,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.onPrimary
    )

    TextField(
        colors = TextFieldDefaults.colors(
            focusedContainerColor = datos.listaColor.get(0),
            unfocusedContainerColor = datos.listaColor.get(0),
            focusedIndicatorColor = datos.listaColor.get(2),
            cursorColor = datos.listaColor.get(3),
            focusedTextColor = datos.listaColor.get(1),
            unfocusedTextColor = datos.listaColor.get(1)
        ),
        modifier = datos.modif,
        value = datos.txtContenido,
        onValueChange = { txtNuevo -> datos.txtContenido = datos.accion(txtNuevo) },
        label = { Text(datos.msjPregunta, color = datos.listaColor.get(1)) },
        visualTransformation =
            if (visible) VisualTransformation.None
            else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
            trailingIcon = {
                IconButton(onClick = onclickIcon
                ) {
                    Icon(
                        imageVector = if (visible)
                            Icons.Default.Favorite
                        else
                            Icons.Default.FavoriteBorder,
                        contentDescription = if (visible)
                            "Ocultar contraseña"
                        else
                            "Mostrar contraseña",
                        tint = datos.listaColor[1]
                    )
                }

            }
        )
    }
@Preview
@Composable
fun PrevComponenteTextField() {
    val mu: MutableState<String> = remember { mutableStateOf(value = "as") }
    //ComponenteTextField(DatosTextField(txtContenido = mu))
}