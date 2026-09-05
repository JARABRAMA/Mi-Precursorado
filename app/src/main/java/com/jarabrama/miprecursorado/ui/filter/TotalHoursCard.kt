package com.jarabrama.miprecursorado.ui.filter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jarabrama.miprecursorado.R
import java.time.Duration

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun TotalHoursCard(duration: Duration) {
  Card(
    modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
      contentColor = MaterialTheme.colorScheme.onPrimary,
      containerColor = MaterialTheme.colorScheme.primary
    )
  ) {
    Spacer(Modifier.padding(vertical = 16.dp))

    if (duration.isZero) {
      NotHoursJetMessage()
    } else {
      DurationText(duration)
    }
    Spacer(Modifier.padding(vertical = 16.dp))

  }
}

@Composable
private fun ColumnScope.NotHoursJetMessage() {
  Text(
    stringResource(R.string.no_horas_aun), style = MaterialTheme.typography.titleLarge,
    fontWeight = FontWeight.ExtraBold, modifier = Modifier.align(Alignment.CenterHorizontally)
  )
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
private fun ColumnScope.DurationText(duration: Duration) {
  Text(
    stringResource(R.string.total_del_mes), style = MaterialTheme.typography.titleMedium,
    modifier = Modifier.align(Alignment.CenterHorizontally)
  )

  Spacer(Modifier.padding(vertical = 5.dp))

  Row(
    horizontalArrangement = Arrangement.spacedBy(
      16.dp,
      alignment = Alignment.CenterHorizontally
    ), modifier = Modifier.fillMaxWidth()
  ) {
    Text(
      stringResource(R.string.horas, duration.toHoursPart()),
      style = MaterialTheme.typography.titleLarge,
      fontWeight = FontWeight.ExtraBold
    )
    Text(
      stringResource(R.string.minutos, duration.toMinutesPart()),
      style = MaterialTheme.typography.titleLarge,
      fontWeight = FontWeight.ExtraBold
    )
  }
}

@RequiresApi(Build.VERSION_CODES.S)
@Preview
@Composable
private fun PreviewTotalHoursCard() {
  MaterialTheme {
    TotalHoursCard(
      duration = Duration.ofMinutes(12200L)
    )
  }
}

@RequiresApi(Build.VERSION_CODES.S)
@Preview
@Composable
private fun PreviewTotalHoursCardWithZeroDuration() {
  MaterialTheme {
    TotalHoursCard(
      duration = Duration.ofMinutes(0)
    )
  }
}