package com.example.examen.navigation

sealed class Screen(val route: String) {
    object MostrarLibrosFavoritos : Screen("MostrarLibrosFavoritos")
    object BuscarLibro: Screen("BuscarLibro")

}