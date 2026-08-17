package com.jarabrama.miprecursorado.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.jarabrama.miprecursorado.domain.PreachingDayRepository
import com.jarabrama.miprecursorado.domain.exceptions.PreachingDayNotFoundException
import java.time.Duration
import java.time.LocalDate

class UpdatePreachingDayDurationUseCase(private val repository: PreachingDayRepository) {

  @RequiresApi(Build.VERSION_CODES.O)
  fun execute(date: LocalDate, duration: Duration) {
    val actualPreachingDay = repository.findByDate(date)
      ?: throw PreachingDayNotFoundException("No se encontró el día de predicación para la fecha especificada")

    val newDuration = actualPreachingDay.preachingDuration.plus(duration);
    repository.updateDuration(date = date, newDuration)
  }
}