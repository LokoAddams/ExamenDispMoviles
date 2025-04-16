package com.example.examen.buscarLibro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.Libro

@Composable
fun BuscarLibrosUI(viewModel: BuscarLibrosViewModel = hiltViewModel()) {
    val uiState by viewModel.state.collectAsState()
    val query by viewModel.searchQuery

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { viewModel.onSearchQueryChanged(it) },
            label = { Text("Buscar libro") },
            textStyle = TextStyle(color = Color.Black),

            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.searchBooks() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Resultados de búsqueda:")

        // Show results based on uiState
        when (uiState) {
            is BuscarLibrosViewModel.BookUIState.Loading -> {
                Text("Loading...")
            }
            is BuscarLibrosViewModel.BookUIState.Error -> {
                println("estoy mal")
                val msg = (uiState as BuscarLibrosViewModel.BookUIState.Error).message
                Text("Error :" + msg)
            }
            is BuscarLibrosViewModel.BookUIState.Loaded -> {
                val libros = (uiState as BuscarLibrosViewModel.BookUIState.Loaded).list
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(libros) { libro ->
                        BookSearchItem(
                            libro = libro,
                            onLikeClick = { viewModel.likeBook(libro) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BookSearchItem(libro: Libro, onLikeClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text("Título: ${libro.titulo}")
            Text("Autores: ${libro.autores?.joinToString(", ") ?: "No disponible"}")
            Text("Año: ${libro.anioPublicacion?.toString() ?: "No disponible"}")
        }
        Button(onClick = onLikeClick) {
            Text("Favorito")
        }
    }
}
