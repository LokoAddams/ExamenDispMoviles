package com.example.examen.book

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.domain.Libro
import com.example.examen.R

@Composable
fun BookSearchItem(libro: Libro, onLikeClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(stringResource(R.string.titulo) + "${libro.titulo}")
            Text( stringResource(R.string.autores) + "${libro.autores?.joinToString(", ") ?: stringResource(
                R.string.noDsiponible
            )}")
            Text(stringResource(R.string.anio) + "${libro.anioPublicacion?.toString() ?: stringResource(
                R.string.noDsiponible
            )}")
        }
        Button(onClick = onLikeClick) {
            Text(stringResource(R.string.fav))
        }
    }
}