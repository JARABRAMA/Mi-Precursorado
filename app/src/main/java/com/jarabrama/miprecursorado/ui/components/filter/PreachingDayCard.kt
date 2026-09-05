package com.jarabrama.miprecursorado.ui.components.filter

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jarabrama.miprecursorado.R
import com.jarabrama.miprecursorado.ui.theme.AppTheme
import com.jarabrama.miprecursorado.ui.utils.getDayOfTheWeekString
import java.time.Duration
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun PreachingDayCard(date: LocalDate, duration: Duration) {
  Surface(
    shadowElevation = 1.dp,
    shape = RoundedCornerShape(8.dp)
  ) {
    Row(
      Modifier
        .padding(vertical = 8.dp, horizontal = 12.dp)
        .fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        DateString(date = date)
        DurationString(duration = duration)
      }
      IconButton(
        {},
        colors = IconButtonDefaults.iconButtonColors(
          containerColor = Color.Transparent
        )
      ) {
      Icon(
        painterResource(R.drawable.more_vert),
        "more options",
        modifier = Modifier.background(Color.Transparent)
      )
    }
    }
  }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateString(date: LocalDate) {
  Row {
    Text(
      text = getDayOfTheWeekString(date.dayOfWeek),
      style = MaterialTheme.typography.titleSmall
    )
    Spacer(Modifier.width(4.dp))
    Text(
      text = "${date.dayOfMonth}",
      style = MaterialTheme.typography.titleSmall
    )
  }
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun DurationString(duration: Duration) {
  Row(
    horizontalArrangement = Arrangement.spacedBy(4.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      painterResource(R.drawable.clock), "clock icon",
      modifier = Modifier.size(12.dp)
    )
    Text(text = stringResource(R.string.horas, duration.toHoursPart()), style = MaterialTheme.typography.bodyMedium)
    Text(text = stringResource(R.string.minutos, duration.toMinutesPart()), style = MaterialTheme.typography.bodyMedium)
  }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Light mode", showBackground = true)
@Preview(
  name = "Dark mode",
  showBackground = true,
  uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PreviewPreachingDay() {
  AppTheme {
    PreachingDayCard(LocalDate.now(), Duration.ofMinutes(125))
  }
}