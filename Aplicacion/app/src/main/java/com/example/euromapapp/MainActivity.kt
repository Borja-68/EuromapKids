package com.example.euromapapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.euromapapp.ui.theme.EuromapAppTheme
import com.example.euromapapp.vista.paginas.PaginaCambioDatos
import com.example.euromapapp.vista.paginas.PaginaClasificacion
import com.example.euromapapp.vista.paginas.PaginaInicioSesion
import com.example.euromapapp.vista.paginas.PaginaJuego
import com.example.euromapapp.vista.paginas.PaginaPerfil
import com.example.euromapapp.vista.paginas.PaginaPrincipal
import com.example.euromapapp.vista.paginas.PaginaRegistrarse
import com.example.euromapapp.vista.paginas.PaginaSeleccionNivel

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
                        CargaPagina()
                    }
                }
            }
        }
    }
}

@Composable
fun CargaPagina() {
//                PaginaInicioSesion()
//                PaginaSeleccionNivel()
//                PaginaRegistrarse()
                PaginaPerfil()
//                PaginaPrincipal()
//                PaginaJuego()
//                PaginaCambioDatos()
//                PaginaClasificacion()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EuromapAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                  PaginaInicioSesion()


            }
        }
    }
}

