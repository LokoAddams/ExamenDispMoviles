package com.example.examen.responseStates

import com.example.domain.Libro


sealed class BookUIState {
    object Loading: BookUIState()
    data class Loaded(val list: List<Libro>): BookUIState()
    data class Error(val message: String = "Libro no encontrado"): BookUIState()
}