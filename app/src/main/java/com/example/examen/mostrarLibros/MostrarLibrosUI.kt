package com.example.examen.mostrarLibros


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//import androidx.compose.material.Icon
//import androidx.compose.material.IconButton
//import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.examen.book.BookSearchItem
import com.example.examen.R
import com.example.examen.responseStates.BookUIState

@Composable
fun MostrarLibrosUI(viewModel: MostrarLibrosViewModel = hiltViewModel(),  onBackPressed: () -> Unit) {
    LaunchedEffect(Unit) {
        viewModel.loadSavedBooks()
    }
    val uiState by viewModel.state.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        IconButton(
            onClick = onBackPressed,
            content = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        )
        when (uiState) {
            is BookUIState.Loading -> {
                Text(stringResource(R.string.cargando))
            }
            is BookUIState.Error -> {
                // Asumiendo que BookUIState.Error tiene un campo 'message'
                val errorMessage = (uiState as BookUIState.Error).message
                Text(stringResource(R.string.error) + errorMessage)
            }
            is BookUIState.Loaded -> {
                // Accedemos a la propiedad list sin forzamos el cast
                val libros = (uiState as BookUIState.Loaded).list
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(libros) { libro ->
                        BookSearchItem(
                            libro = libro
                        )
                    }
                }
            }
        }
    }
}


