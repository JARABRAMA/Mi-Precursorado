package com.jarabrama.miprecursorado.usecases

import com.jarabrama.miprecursorado.domain.PreachingDayRepository
import com.jarabrama.miprecursorado.domain.exceptions.PreachingDayNotFoundException
import com.jarabrama.miprecursorado.domain.model.PreachingDay
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.Duration
import java.time.LocalDate


class UpdatePreachingDayDurationUseCaseTest {

  @Mock
  private lateinit var repository: PreachingDayRepository

  private lateinit var updatePreachingDayDurationUseCase: UpdatePreachingDayDurationUseCase

  @Before
  fun setUp() {
    MockitoAnnotations.openMocks(this)
    updatePreachingDayDurationUseCase = UpdatePreachingDayDurationUseCase(repository)
  }

  @Test
  fun `when preaching day does not exists then throw exception`() {
    val date = LocalDate.now()
    whenever(repository.findByDate(date)).thenReturn(null)

    val ex = assertThrows(PreachingDayNotFoundException::class.java) {
      updatePreachingDayDurationUseCase.execute(date, Duration.ofHours(3))
    }

    assertEquals(
      "No se encontró el día de predicación para la fecha especificada",
      ex.message
    )
  }

  @Test
  fun `when preaching day exists then update its duration`() {
    val date = LocalDate.now()
    val actualPreachingDay = PreachingDay(date = date, preachingDuration = Duration.ofHours(2))
    whenever(repository.findByDate(date)).thenReturn(actualPreachingDay)

    updatePreachingDayDurationUseCase.execute(date, Duration.ofHours(3))
    verify(repository).updateDuration(date, Duration.ofHours(5))
  }
}