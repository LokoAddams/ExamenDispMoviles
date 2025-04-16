package com.example.examen.buscarLibro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.examen.book.BookSearchItem
import com.example.examen.R
import com.example.examen.responseStates.BookUIState

@Composable
fun BuscarLibrosUI(viewModel: BuscarLibrosViewModel = hiltViewModel(),
                   onNavigateToMostrarLibros: () -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    val query by viewModel.searchQuery
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(200.dp))

        OutlinedTextField(
            value = query,
            onValueChange = { viewModel.onSearchQueryChanged(it) },
            label = { Text(stringResource(R.string.buscarLibro)) },
            textStyle = TextStyle(color = Color.Black),

            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.searchBooks() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(stringResource(R.string.busc))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(stringResource(R.string.resultadosBus), style = TextStyle(fontSize = 20.sp))

        // Show results based on uiState
        when (uiState) {
            is BookUIState.Loading -> {
                Text(stringResource(R.string.cargando))
            }
            is BookUIState.Error -> {
                println("estoy mal")
                val msg = (uiState as BookUIState.Error).message
                Text(stringResource(R.string.error) + msg)
            }
            is BookUIState.Loaded -> {
                val libros = (uiState as BookUIState.Loaded).list
                LazyColumn(modifier = Modifier.fillMaxWidth()
                    .weight(1f)) {
                    items(libros) { libro ->
                        BookSearchItem(
                            libro = libro,
                            onLikeClick = { viewModel.likeBook(libro) }
                        )
                    }
                }
            }
        }

        Button(
            onClick = { onNavigateToMostrarLibros() },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(stringResource(R.string.favoritos))
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}


