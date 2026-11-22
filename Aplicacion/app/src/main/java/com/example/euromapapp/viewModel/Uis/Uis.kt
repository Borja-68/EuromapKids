package com.example.euromapapp.viewModel.Uis

import TarjetaClasificacion
import TarjetaResultado
import TarjetaResultadoPerfil
import com.example.euromapapp.R
import com.example.euromapapp.componentes.DatosTituloCaja

data class PaginaJuegoUiState(
    val idMapa: Int = 1,
    val idioma: String = "",
    var correctas: Int = 0,
    var Incorrectas: Int =0,
    var tiempoFinalMs: Long =0
)

data class PaginaSeleccionNivelUiState(
    val idMapa: Int = 1,
    val nombreMapa: String = "",
    var imagenMapa: Int = R.drawable.avatar_picaxu,
    var puntuacion: TarjetaResultado = TarjetaResultado()
)

data class PaginaClasificacionUiState(
    val nombremapa: String = "NombreMapa",
    var clasificacion: List<TarjetaClasificacion> = listOf(
        TarjetaClasificacion(posicion = 1, nombreUsuario = "as", aciertos = "10/10", tiempo= "200"),
        TarjetaClasificacion(posicion = 1, nombreUsuario = "as", aciertos = "10/10", tiempo= "200"),
        TarjetaClasificacion(posicion = 1, nombreUsuario = "as", aciertos = "10/10", tiempo= "200"),
        TarjetaClasificacion(posicion = 1, nombreUsuario = "as", aciertos = "10/10", tiempo= "200"),
        TarjetaClasificacion(posicion = 1, nombreUsuario = "as", aciertos = "10/10", tiempo= "200"),
        TarjetaClasificacion(posicion = 1, nombreUsuario = "as", aciertos = "10/10", tiempo= "200"),
        TarjetaClasificacion(posicion = 1, nombreUsuario = "as", aciertos = "10/10", tiempo= "200"),
        TarjetaClasificacion(posicion = 1, nombreUsuario = "as", aciertos = "10/10", tiempo= "200"),
        TarjetaClasificacion(posicion = 1, nombreUsuario = "as", aciertos = "10/10", tiempo= "200"),
        TarjetaClasificacion(posicion = 1, nombreUsuario = "as", aciertos = "10/10", tiempo= "200"),
        )
)

data class PaginaPerfilUiState(
    val imagenPerfil: Int = R.drawable.avatar_picaxu,
    val nombreUsuario: String="aitana",
    val correoUsuario: String="aitana@gmail.com",
    val tarjetasUsuario: List<TarjetaResultadoPerfil> = listOf(TarjetaResultadoPerfil(imagen = R.drawable.avatar_picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        TarjetaResultadoPerfil(imagen = R.drawable.rango_a, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        TarjetaResultadoPerfil(imagen = R.drawable.rango_b, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        TarjetaResultadoPerfil(imagen = R.drawable.rango_c, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        TarjetaResultadoPerfil(imagen = R.drawable.rango_f, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        TarjetaResultadoPerfil(imagen = R.drawable.rango_s, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        TarjetaResultadoPerfil(imagen = R.drawable.rango_d, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        TarjetaResultadoPerfil(imagen = R.drawable.avatar_picaxu, aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        )
)

data class TarjetaDatosPerfilUiState(
    val tipoMapa: String,
    val nombreMapa: String,
    val rango: Char,
    val aciertos: String,
    val tiempo: String
)

data class DatosPaginaLoginUiState(
    var nombreOCorreo: String="",
    var contrasena: String="",
    var contrasenaVisible: Boolean= true
)

data class DatosPaginaRegistroUiState(
    var nombre: String="",
    var correo: String="",
    var contrasena: String="",
    var contrasenaVisible: Boolean= true,
    var imagenPerfil: List<Int> =R.drawable::class.java.fields
        .filter { it.name.startsWith("avatar_") }
        .sortedBy { it.name }
        .map { it.getInt(null) },
    var idiomas: List<String> =listOf("es","en","la"),
    var modoOscuro: Boolean =true
)

data class DatosPaginaCambioDatosUiState(
    var nombre: String="",
    var contrasena: String="",
    var contrasenaVisible: Boolean= true,
    var imagenPerfil: List<Int> =R.drawable::class.java.fields
        .filter { it.name.startsWith("avatar_") }
        .sortedBy { it.name }
        .map { it.getInt(null) },
    var idiomas: List<String> =listOf("es","en","la"),
    var modoOscuro: Boolean =true
)

data class DatosPaginaPrincipalUiState(
    var tipos: List<DatosTituloCaja> = listOf(DatosTituloCaja(titulo = "Politicos"),
        DatosTituloCaja(titulo = "Politicos"),
        DatosTituloCaja(titulo = "Politicos"),
        DatosTituloCaja(titulo = "Politicos"),
        DatosTituloCaja(titulo = "Politicos"))
)