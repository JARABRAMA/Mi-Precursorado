package com.jarabrama.miprecursorado.ui.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jarabrama.miprecursorado.R
import java.time.DayOfWeek

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getDayOfTheWeekString(day: DayOfWeek): String {
  return when (day) {
    DayOfWeek.MONDAY -> stringResource(R.string.monday)
    DayOfWeek.TUESDAY -> stringResource(R.string.Tuesday)
    DayOfWeek.WEDNESDAY -> stringResource(R.string.wednesday)
    DayOfWeek.THURSDAY -> stringResource(R.string.thursday)
    DayOfWeek.FRIDAY -> stringResource(R.string.friday)
    DayOfWeek.SATURDAY -> stringResource(R.string.saturday)
    DayOfWeek.SUNDAY -> stringResource(R.string.sunday)
  }
}