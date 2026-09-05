package com.jarabrama.miprecursorado.ui.components.filter

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.jarabrama.miprecursorado.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterTopAppBar(
  currentMonth: String,
  onSelectMonth: () -> Unit
) {
  TopAppBar(
    title = { Text(text = currentMonth, style = MaterialTheme.typography.titleLarge) },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.surface,
      titleContentColor = MaterialTheme.colorScheme.onSurface,
    ),
    actions = {
      IconButton(
        onClick = onSelectMonth,
        colors = IconButtonDefaults.iconButtonColors(
          containerColor = MaterialTheme.colorScheme.primary,
          contentColor = MaterialTheme.colorScheme.onPrimary
        )
      ) {
        Icon(
          painterResource(R.drawable.calendar),
          contentDescription = "Escoge el mes"
        )
      }
    }
  )
}


@Preview
@Composable
private fun FilterTopBarPreview() {
  FilterTopAppBar("Agosto") {}
}