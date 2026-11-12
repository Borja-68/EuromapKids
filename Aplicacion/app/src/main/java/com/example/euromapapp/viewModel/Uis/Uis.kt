package com.example.euromapapp.viewModel.Uis

data class PaginaJuegoUiState(
    val idMapa: Int = 1,
    val tipoMapa: String = "",
    var idioma: String = ""
)

data class PaginaPerfilUiState(
    val imagenPerfil: String = "R.drawable.perfil",
    val nombreUsuario: String="",
    val correoUsuario: String="@gmail.com",
    val tarjetasUsuario: List<TarjetaUiDatosPerfil> = listOf()
)

data class TarjetaUiDatosPerfil(
    val tipoMapa: String,
    val nombreMapa: String,
    val rango: Char,
    val aciertos: String,
    val tiempo: String
)
