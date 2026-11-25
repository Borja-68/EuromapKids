package com.example.euromapapp.vista.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.euromapapp.viewModel.vm.MainViewModel
import com.example.euromapapp.vista.paginas.PaginaCambioDatos
import com.example.euromapapp.vista.paginas.PaginaClasificacion
import com.example.euromapapp.vista.paginas.PaginaInicioSesion
import com.example.euromapapp.vista.paginas.PaginaJuego
import com.example.euromapapp.vista.paginas.PaginaPerfil
import com.example.euromapapp.vista.paginas.PaginaPrincipal
import com.example.euromapapp.vista.paginas.PaginaRegistrarse
import com.example.euromapapp.vista.paginas.PaginaSeleccionNivel


object Routes {
    const val paginaPrincipal = "principal"
    const val PaginaInicioSesion = "login"
    const val paginaPerfil = "perfil"
    const val paginaRegistro = "sign"
    const val paginaCambioDatos = "cambio"
    const val paginaSeleccionNivel = "seleccionNivel/{tipoMapa}/{idMapa}"
    const val paginaJuego = "juego/{idMapa}/{tipoMapa}"
    const val paginaClasificacion = "paginaClasificacion/{idMapa}/{tipoMapa}"
}

/**
 * componente con el que se navega en la aplicación
 * @param navController lo que permite la navegacion entre páginas
 * @param viewMain viewmodel del main, utilizado para saber si ya hay un usuario loguado y para quitar el scaffold y floating buton
 */
@Composable
fun EuromapNavGraph(navController: NavHostController, viewMain: MainViewModel) {

    NavHost(navController = navController, startDestination = Routes.PaginaInicioSesion) {
        composable(Routes.paginaPrincipal) {
//            viewMain.ponTodo()
            PaginaPrincipal(
                onItemClick = { tipoMapa,seleccionNivel ->
                    navController.navigate("seleccionNivel/$tipoMapa/$seleccionNivel")
                }
            )
        }

        composable(Routes.paginaPerfil) {
//            viewMain.quitaTodo()
            PaginaPerfil(
                accionSalida = {
                    navController.navigate("principal") }
            )
        }

        composable(Routes.paginaRegistro) {
//            viewMain.quitaTodo()
            PaginaRegistrarse(
                modoOscuro = viewMain,
                onItemClick = {
                    navController.navigate("login")
                },
                onSignUpClick = {navController.navigate("principal")}
            )

        }

        composable(Routes.paginaCambioDatos) {
//            viewMain.ponTodo()
            PaginaCambioDatos(
                modoOscuro = viewMain,
                onItemClick = {
                    navController.navigate("perfil")
                }
            )
        }

        composable(Routes.PaginaInicioSesion) {
            if(true) {
//            if(!viewMain.uiState.collectAsState().value.logueado) {
//                viewMain.quitaTodo()
                PaginaInicioSesion(
                    onItemClick = { navController.navigate("principal") },
                    onSignUpClick = { navController.navigate("sign") }
                )
            }
            else navController.navigate("principal")
        }

        composable(
            route = Routes.paginaSeleccionNivel,
            arguments = listOf(navArgument("tipoMapa") { type = NavType.StringType },
                navArgument("idMapa") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val idMapa = backStackEntry.arguments?.getInt("idMapa") ?: return@composable
            val tipoMapa = backStackEntry.arguments?.getString("tipoMapa") ?: return@composable

//            viewMain.quitaTodo()
            PaginaSeleccionNivel(
                tipo = tipoMapa,
                idMapa = idMapa,
                accionSalida = { navController.navigate("principal") },
                accionClickImagen = {idMapa,tipoMapa->
                    navController.navigate("juego/$idMapa/$tipoMapa")
                },
                onClickClasificacion = {idMapa,tipoMapa ->
                    navController.navigate("paginaClasificacion/$idMapa/$tipoMapa")
                })
        }

        composable(
            route = Routes.paginaJuego,
            arguments = listOf(navArgument("idMapa") { type = NavType.IntType }
               ,navArgument("tipoMapa") {type = NavType.StringType}
            )
        ) { backStackEntry ->
            val idMapa = backStackEntry.arguments?.getInt("idMapa") ?: return@composable
            val tipoMapa = backStackEntry.arguments?.getString("tipoMapa") ?: return@composable

//            viewMain.quitaTodo()
            PaginaJuego(
                idMapa = idMapa,
                tipoMapa=tipoMapa,
                accionFin = {tipoMapa,idMapa ->
                    navController.navigate("seleccionNivel/$tipoMapa/$idMapa")
                }
            )
        }

        composable(
            route = Routes.paginaClasificacion,
            arguments = listOf(navArgument("idMapa") { type = NavType.IntType }
                ,navArgument("idMapa") {type = NavType.StringType}
            )
        ) {backStackEntry ->
            val idMapa = backStackEntry.arguments?.getInt("idMapa") ?: return@composable
            val tipoMapa = backStackEntry.arguments?.getString("tipoMapa") ?: return@composable

//            viewMain.quitaTodo()
            PaginaClasificacion(
                accionSalida = {idMapa,tipoMapa ->
                    navController.navigate("seleccionNivel/$tipoMapa/$idMapa")
                }
            )
        }

    }
}
