package com.example.euromapapp.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.euromapapp.R

data class DatosSwitch (var valor: Boolean=true,val modifier: Modifier = Modifier,val accion: (Boolean) -> Unit = {})

@Composable
fun ComponenteSwitch(valor: DatosSwitch){
    Switch(checked = valor.valor,
        modifier = valor.modifier,
        onCheckedChange = valor.accion)
}

@Composable
fun ComponenteSwitchConTexto(valor: DatosSwitch,msg: String){
    Column(modifier = valor.modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    Text(text = msg)
    Switch(checked = valor.valor,
        modifier = valor.modifier,
        onCheckedChange = valor.accion)
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSwitch() {
    var estado1 = remember {  mutableStateOf(true)}
    var estado2 = remember {  mutableStateOf(true)}
    var valor1= DatosSwitch(estado1.value, accion = { estado1.value = !estado1.value })
    var valor2= DatosSwitch(estado2.value, accion = { estado2.value = !estado2.value })

    Column {
        ComponenteSwitch(valor1)
        ComponenteSwitchConTexto(valor2,"Modo oscuro")
    }
}