package com.example.euromapapp.viewModel.Uis

import TarjetaClasificacion
import TarjetaResultado
import TarjetaResultadoPerfil
import com.example.euromapapp.R
import com.example.euromapapp.repositorio.DTO.PuntuacionDTO
import com.example.euromapapp.repositorio.DTO.PuntuacionesDTO
import com.example.euromapapp.repositorio.DTO.TipoMapaDTO

data class PaginaJuegoUiState(
    val idMapa: Int = 1,
    val idioma: String = "",
    var correctas: Int = 0,
    var Incorrectas: Int =0,
    var tiempoFinalMs: Long =0
)

data class MainUiState(
    var modoOscuro: Boolean =true,
    val topbarMain: Boolean=false,
    val topbarAccionAtras: Boolean=false,
    val mensaje: String="",
    val idioma: String= "es",
    val logueado: Boolean=false,
    val accionRegreso: () -> Unit={}
)
data class PaginaSeleccionNivelUiState(
    val mapas: List<TipoMapaDTO> = listOf<TipoMapaDTO>(TipoMapaDTO(),
        TipoMapaDTO(2,R.drawable.rango_c)
    ),
    var nombreMapa: String = "",
    var puntuacion: PuntuacionDTO = PuntuacionDTO(
        rango = 'a',
        aciertos = "1/10",
        tiempo = "10:10:10")
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
    val tarjetasUsuario: List<PuntuacionesDTO> = listOf(PuntuacionesDTO(rango = 'A', aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        PuntuacionesDTO(rango = 'A', aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        PuntuacionesDTO(rango = 'A', aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        PuntuacionesDTO(rango = 'A', aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        PuntuacionesDTO(rango = 'A', aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        PuntuacionesDTO(rango = 'A', aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        PuntuacionesDTO(rango = 'A', aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
        PuntuacionesDTO(rango = 'A', aciertos = "7/10", nombreMapa = "asa", tipo = "es", tiempo = "10:10:10"),
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
    var idiomas: List<String> =listOf("es","en"),
)

data class DatosPaginaCambioDatosUiState(
    var nombre: String="",
    var contrasena: String="",
    var contrasenaVisible: Boolean= true,
    var imagenPerfil: List<Int> =R.drawable::class.java.fields
        .filter { it.name.startsWith("avatar_") }
        .sortedBy { it.name }
        .map { it.getInt(null) },
    var idiomas: List<String> =listOf("es","en"),
)

data class DatosPaginaPrincipalUiState(
    var tipos: List<DatosTarjetaPrincipal> = listOf(DatosTarjetaPrincipal(titulo = "Politicos"),
        DatosTarjetaPrincipal(titulo = "Politicos"),
        DatosTarjetaPrincipal(titulo = "Rios"),
        DatosTarjetaPrincipal(titulo = "Banderas"))
)

data class DatosTarjetaPrincipal(
    var titulo: String
)
