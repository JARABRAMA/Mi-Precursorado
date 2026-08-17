package com.jarabrama.miprecursorado.usecases

import com.jarabrama.miprecursorado.domain.PreachingDayRepository
import com.jarabrama.miprecursorado.domain.exceptions.InvalidDateException
import com.jarabrama.miprecursorado.domain.exceptions.PreachingDayAlreadyExistsException
import com.jarabrama.miprecursorado.domain.model.PreachingDay
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.Duration
import java.time.LocalDate

class SavePreachingDayUseCaseTest {

    @Mock
    private lateinit var repository: PreachingDayRepository

    private lateinit var savePreachingDayUseCase: SavePreachingDayUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        savePreachingDayUseCase = SavePreachingDayUseCase(repository)
    }

    @Test
    fun `when day is after current day then throw exception`() {
        val date = LocalDate.now().plusDays(4)
        val duration = Duration.ofHours(2)

        val ex = assertThrows(InvalidDateException::class.java) {
            savePreachingDayUseCase.execute(date, duration)
        }

        assertEquals("No se puede registrar un día posterior a la fecha actual", ex.message)
    }

    @Test
    fun `when day already exists then throw exception`() {
        val date = LocalDate.now()
        val duration = Duration.ofHours(2)
        val existingDay = PreachingDay(date = date, preachingDuration = duration)

        whenever(repository.findByDate(date)).thenReturn(existingDay)

        val ex = assertThrows(PreachingDayAlreadyExistsException::class.java) {
            savePreachingDayUseCase.execute(date, duration)
        }

        assertEquals(
            "Este día ya tiene un tiempo registrado. Si continúas, el nuevo tiempo se sumará al tiempo registrado anteriormente. ¿Deseas continuar?",
            ex.message
        )
    }

    @Test
    fun `when day is valid and does not exist then save`() {
        val date = LocalDate.now()
        val duration = Duration.ofHours(2)

        whenever(repository.findByDate(date)).thenReturn(null)

        savePreachingDayUseCase.execute(date, duration)

        verify(repository).save(any<PreachingDay>())
    }
}
