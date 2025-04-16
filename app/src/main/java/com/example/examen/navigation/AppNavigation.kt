package com.example.examen.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.examen.buscarLibro.BuscarLibrosUI
import com.example.examen.mostrarLibros.MostrarLibrosUI

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.BuscarLibro .route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }

    ) {
        composable(Screen.BuscarLibro.route) {
            BuscarLibrosUI(
                onNavigateToMostrarLibros = {
                    navController.navigate(Screen.MostrarLibrosFavoritos.route)
                }
            )
        }

        composable(Screen.MostrarLibrosFavoritos.route) {
            MostrarLibrosUI( onBackPressed = { navController.popBackStack()})
        }



    }


}