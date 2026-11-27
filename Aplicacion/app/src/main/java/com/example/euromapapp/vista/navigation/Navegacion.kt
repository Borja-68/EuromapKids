package com.example.euromapapp.vista.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
            viewMain.setTopBarMainConcreto(true)
            viewMain.setTopbarAccionAtrasConcreto(false)
            viewMain.setMensaje("")

            PaginaPrincipal(
                idioma = viewMain.getIdioma(),
                onItemClick = { tipoMapa,seleccionNivel ->
                    navController.navigate("seleccionNivel/$tipoMapa/$seleccionNivel")
                }
            )
        }

        composable(Routes.paginaPerfil) {

            val accionAtras = remember(navController) {
                { navController.navigate("principal") }
            }

            viewMain.setTopBarMainConcreto(false)
            viewMain.setTopbarAccionAtrasConcreto(true)
            viewMain.setAccionRegreso(accionAtras)
            viewMain.setMensaje("titulo_perfil")

            PaginaPerfil(
                idioma = viewMain.getIdioma(),
                accionCambioDatos = {
                    navController.navigate("cambio")
                }
            )
        }

        composable(Routes.paginaRegistro) {
            val idiomaPrevio= remember {  viewMain.getIdioma()}
            val modoOscuroPrevio= remember {  viewMain.getModoOscuro()}
            val accionAtras = remember(navController, modoOscuroPrevio) {
                {
                    viewMain.setIdioma(idiomaPrevio)
                    viewMain.setModoOscuroConcreto(modoPrevio = modoOscuroPrevio) {
                        navController.navigate("login")
                    }
                }
            }

            viewMain.setTopBarMainConcreto(false)
            viewMain.setTopbarAccionAtrasConcreto(true)
            viewMain.setAccionRegreso(accionAtras)
            viewMain.setMensaje("mensaje_btn_crear")

            PaginaRegistrarse(
                main = viewMain,
                onSignUpClick = {navController.navigate("principal")}
            )

        }

        composable(Routes.paginaCambioDatos) {
            val idiomaPrevio= remember {  viewMain.getIdioma()}
            val modoOscuroPrevio= remember {  viewMain.getModoOscuro()}
            val accionAtras = remember(navController, modoOscuroPrevio) {
                {
                    viewMain.setIdioma(idiomaPrevio)
                    viewMain.setModoOscuroConcreto(modoPrevio = modoOscuroPrevio) {
                        navController.navigate("perfil")
                    }
                }
            }

            viewMain.setTopBarMainConcreto(false)
            viewMain.setTopbarAccionAtrasConcreto(true)
            viewMain.setAccionRegreso(accionAtras)
            viewMain.setMensaje("mensaje_boton_edicion")

            PaginaCambioDatos(
                main = viewMain,
                onItemClick = {
                    navController.navigate("perfil")
                }
            )
        }

        composable(Routes.PaginaInicioSesion) {
            viewMain.setTopBarMainConcreto(false)
            viewMain.setTopbarAccionAtrasConcreto(false)

            if(!viewMain.getLogueado()) {
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
            val accionAtras = remember(navController) {
                { navController.navigate("principal") }
            }

            viewMain.setTopBarMainConcreto(false)
            viewMain.setTopbarAccionAtrasConcreto(true)
            viewMain.setAccionRegreso(accionAtras)
            viewMain.setMensaje("titulo_tipomapa_"+tipoMapa.lowercase())

            PaginaSeleccionNivel(
                idioma = viewMain.getIdioma(),
                tipo = tipoMapa,
                idMapa = idMapa,
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
            val accionAtras = remember(navController, tipoMapa, idMapa) {
                { navController.navigate("seleccionNivel/$tipoMapa/$idMapa") }
            }

            viewMain.setTopBarMainConcreto(false)
            viewMain.setTopbarAccionAtrasConcreto(true)
            viewMain.setAccionRegreso(accionAtras)
            viewMain.setMensaje("titulo_juego")

            PaginaJuego(
                idMapa = idMapa,
                tipoMapa=tipoMapa,
                idioma = viewMain.getIdioma(),
                accionFin = {tipoMapa,idMapa ->
                    navController.navigate("seleccionNivel/$tipoMapa/$idMapa")
                }
            )
        }

        composable(
            route = Routes.paginaClasificacion,
            arguments = listOf(navArgument("idMapa") { type = NavType.IntType },
                navArgument("tipoMapa") {type = NavType.StringType}
            )
        ) {backStackEntry ->
            val idMapa = backStackEntry.arguments?.getInt("idMapa") ?: return@composable
            val tipoMapa = backStackEntry.arguments?.getString("tipoMapa") ?: return@composable
            val accionAtras = remember(navController, tipoMapa, idMapa) {
                { navController.navigate("seleccionNivel/$tipoMapa/$idMapa") }
            }

            viewMain.setTopBarMainConcreto(false)
            viewMain.setTopbarAccionAtrasConcreto(true)
            viewMain.setAccionRegreso(accionAtras)
            viewMain.setMensaje("titulo_clasificacion")

            PaginaClasificacion()
        }

    }
}
