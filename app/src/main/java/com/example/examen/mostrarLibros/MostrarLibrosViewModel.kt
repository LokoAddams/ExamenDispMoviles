package com.example.examen.mostrarLibros


import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.NetworkResult
import com.example.domain.Libro
import com.example.examen.responseStates.BookUIState
import com.example.usecases.ShowSavedBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MostrarLibrosViewModel @Inject constructor(
    private val showSavedBooks: ShowSavedBooks
) : ViewModel() {

    // Lista mutable de libros guardados (favoritos)
    private val _state = MutableStateFlow<BookUIState>(BookUIState.Loading)
    val state: StateFlow<BookUIState> = _state

    // Carga los libros guardados, por ejemplo, desde una base de datos local.
    fun loadSavedBooks() {


        viewModelScope.launch {
            _state.value = BookUIState.Loading

            val response = withContext(Dispatchers.IO) {
                showSavedBooks.save()
            }
            println("he retornado")
            when (response) {
                is NetworkResult.Error -> {
                    println("estoy mal")
                    _state.value = BookUIState.Error(response.error)
                }
                is NetworkResult.Success -> {
                    _state.value = BookUIState.Loaded(response.data)
                }
            }
        }
    }

//    // Función para eliminar un libro guardado.
//    fun removeSavedBook(book: Libro) {
//        // Llamada al caso de uso real:
//        // removeBookUseCase(book)
//        _state.value = _state.value.filter { it.id != book.id }
//    }
}
