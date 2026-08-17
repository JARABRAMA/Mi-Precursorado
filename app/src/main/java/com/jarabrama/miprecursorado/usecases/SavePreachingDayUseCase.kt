package com.jarabrama.miprecursorado.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.jarabrama.miprecursorado.domain.exceptions.PreachingDayAlreadyExistsException
import com.jarabrama.miprecursorado.domain.PreachingDayRepository
import com.jarabrama.miprecursorado.domain.exceptions.InvalidDateException
import com.jarabrama.miprecursorado.domain.model.PreachingDay
import java.time.Duration
import java.time.LocalDate

class SavePreachingDayUseCase(
  private val repository: PreachingDayRepository
) {
  @RequiresApi(Build.VERSION_CODES.O)
  fun execute(date: LocalDate, duration: Duration) {
    val alreadyExists = repository.findByDate(date) != null
    if (alreadyExists) {
      throw PreachingDayAlreadyExistsException("Este día ya tiene un tiempo registrado. Si continúas, el nuevo tiempo se sumará al tiempo registrado anteriormente. ¿Deseas continuar?")
    }
    val now = LocalDate.now()
    if (date.dayOfYear > now.dayOfYear)
      throw InvalidDateException("No se puede registrar un día posterior a la fecha actual")

    val newPreachingDay = PreachingDay(date = date, preachingDuration = duration)
    repository.save(newPreachingDay)
  }
}