package com.example.euromapapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.euromapapp.componentes.ComponenteMapaInteractivo
import com.example.euromapapp.ui.theme.EuromapAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EuromapAppTheme {
                val correctas = remember { mutableIntStateOf(0) }
                val incorrectas = remember { mutableIntStateOf(0) }
                val tiempoFinalMs = remember { mutableLongStateOf(0L) }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(Modifier.fillMaxSize().padding(innerPadding)) {
                        ComponenteMapaInteractivo(
                            idioma = "es",
                            mapaNombre = "mp_espana",
                            modifier = Modifier.fillMaxSize(),
                            onCorrectas = { correctas.intValue = it },
                            onIncorrectas = { incorrectas.intValue = it },
                            onTiempoFinalizado = { tiempoFinalMs.longValue = it }
                        )
                        Text(
                            text = "Correctas: ${correctas.intValue}   Incorrectas: ${incorrectas.intValue}   Tiempo: ${tiempoFinalMs.longValue} ms",
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(12.dp)
                                .background(Color.White, RoundedCornerShape(8.dp))
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EuromapAppTheme {
        Greeting("Android")
    }
}
