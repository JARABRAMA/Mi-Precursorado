package com.jarabrama.miprecursorado.usecases

import com.jarabrama.miprecursorado.domain.PreachingDayRepository
import com.jarabrama.miprecursorado.domain.model.PreachingDay
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import java.time.Duration
import java.time.LocalDate


class FindPreachingDaysUseCaseTest {

  @Mock
  private lateinit var repository: PreachingDayRepository

  private lateinit var findPreachingDaysUseCase: FindPreachingDaysUseCase

  @Before
  fun setUp() {
    MockitoAnnotations.openMocks(this)
    findPreachingDaysUseCase = FindPreachingDaysUseCase(repository)
  }

  @Test
  fun `return what ever repository return`() {
    val expected = listOf(
      PreachingDay(date = LocalDate.now(), preachingDuration = Duration.ofHours(4)),
      PreachingDay(date = LocalDate.now(), preachingDuration = Duration.ofHours(4)),
      PreachingDay(date = LocalDate.now(), preachingDuration = Duration.ofHours(4)),
      PreachingDay(date = LocalDate.now(), preachingDuration = Duration.ofHours(4)),
    )
    whenever(repository.findByYearAndMoth(any(), any())).thenReturn(expected)
    val actual = findPreachingDaysUseCase.execute(2023, 1)

    assertEquals(expected, actual)
  }
}