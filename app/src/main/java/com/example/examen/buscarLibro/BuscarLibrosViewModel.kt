package com.example.examen.buscarLibro

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.NetworkResult
import com.example.domain.Libro
import com.example.usecases.SearchBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuscarLibrosViewModel @Inject constructor(
    private val searchBooksUseCase: SearchBooksUseCase
) : ViewModel() {

    sealed class BookUIState {
        object Loading: BookUIState()
        data class Loaded(val list: List<Libro>): BookUIState()
        data class Error(val message: String = "Libro no encontrado"): BookUIState()
    }

    private val _state = MutableStateFlow<BookUIState>(BookUIState.Loading)
    val state: StateFlow<BookUIState> = _state

    // Holds the current text entry from the user.
    val searchQuery: MutableState<String> = mutableStateOf("")

    fun onSearchQueryChanged(newValue: String) {
        searchQuery.value = newValue
    }

    fun searchBooks() {
        // Launch the search using the current query
        viewModelScope.launch {
            _state.value = BookUIState.Loading
            val response = searchBooksUseCase.invoke(searchQuery.value)
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

    fun likeBook(book: Libro) {
        // Save or mark the book as liked
    }
}