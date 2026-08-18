package com.jarabrama.miprecursorado.presistence

import com.jarabrama.miprecursorado.domain.model.PreachingDay
import com.jarabrama.miprecursorado.presistence.dao.PreachingDayDao
import com.jarabrama.miprecursorado.presistence.entities.PreachingDayEntity
import com.jarabrama.miprecursorado.presistence.mappers.PreachingDayEntityMapper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.argThat
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID


class PreachingDayRepositoryRoomImplTest {

  @Mock
  private lateinit var dao: PreachingDayDao

  private lateinit var mapper: PreachingDayEntityMapper

  private lateinit var repository: PreachingDayRepositoryRoomImpl


  private lateinit var entities: List<PreachingDayEntity>
  private lateinit var domain: List<PreachingDay>


  fun buildEntitiesAndDomain() {
    this.entities = listOf(
      PreachingDayEntity(
        id = UUID.randomUUID(),
        date = LocalDate.of(2026, 4, 1),
        duration = Duration.ofHours(2),
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
      ),
      PreachingDayEntity(
        id = UUID.randomUUID(),
        date = LocalDate.of(2026, 4, 2),
        duration = Duration.ofHours(1),
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
      ),
      PreachingDayEntity(
        id = UUID.randomUUID(),
        date = LocalDate.of(2026, 4, 3),
        duration = Duration.ofHours(5),
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
      ),
      PreachingDayEntity(
        id = UUID.randomUUID(),
        date = LocalDate.of(2026, 4, 5),
        duration = Duration.ofHours(2),
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
      )
    )
    this.domain = this.entities.map {
      PreachingDay(
        id = it.id,
        date = it.date,
        preachingDuration = it.duration
      )
    }
  }


  @Before
  fun setUp() {
    MockitoAnnotations.openMocks(this)
    mapper = PreachingDayEntityMapper()
    repository = PreachingDayRepositoryRoomImpl(dao, mapper)
  }

  @Test
  fun `when find by id and dao throws error then throws exception`() {
    whenever(
      dao.findPreachingDayByYearAndMonth(
        any(),
        any()
      )
    ).thenThrow(RuntimeException("something was wrong"))

    val ex = assertThrows(RuntimeException::class.java) {
      repository.findByYearAndMoth(2023, 1)
    }
    assertEquals("something was wrong", ex.message)
  }

  @Test
  fun `when find by id returns entities then maps to domain`() {
    buildEntitiesAndDomain()
    whenever(dao.findPreachingDayByYearAndMonth(any(), any()))
      .thenReturn(this.entities)

    val actual = repository.findByYearAndMoth(2023, 1)
    assertEquals(this.domain, actual)
  }

  @Test
  fun `when find by date and dao return null then return null`() {
    whenever(dao.findByDate(any())).thenReturn(null)
    val actual = repository.findByDate(LocalDate.now())
    assertEquals(null, actual)
  }

  @Test
  fun `when find by date and dao throws exception then throws exception`() {
    whenever(dao.findByDate(any())).thenThrow(RuntimeException("something was wrong"))
    val ex = assertThrows(RuntimeException::class.java) {
      repository.findByDate(LocalDate.now())
    }
    assertEquals("something was wrong", ex.message)
  }

  @Test
  fun `when find by date and dao return entity then map to domain`() {
    buildEntitiesAndDomain()
    whenever(dao.findByDate(any())).thenReturn(this.entities[0])
    val actual = repository.findByDate(LocalDate.now())
    assertEquals(this.domain[0], actual)
  }

  @Test
  fun `when update duration and dao throws exception then throws exception`() {
    whenever(dao.updateDuration(any(), any())).thenThrow(RuntimeException("something was wrong"))
    val ex = assertThrows(RuntimeException::class.java) {
      repository.updateDuration(LocalDate.now(), Duration.ofHours(2))
    }
    assertEquals("something was wrong", ex.message)
  }

  @Test
  fun `when update duration dao is called to update`() {
    val date = LocalDate.now()
    val duration = Duration.ofMinutes(45L)
    repository.updateDuration(date, duration)
    verify(dao).updateDuration(date, duration)
  }

  @Test
  fun `when update duration dao is called to update the update date`() {
    val date = LocalDate.now()
    val duration = Duration.ofMinutes(45L)
    repository.updateDuration(date, duration)
    verify(dao).updateUpdatedAt(eq(date), any())
  }

  @Test
  fun `when save and dao throws exception then throws exception`() {
    whenever(dao.insert(any())).thenThrow(RuntimeException("something was wrong"))
    buildEntitiesAndDomain()
    val ex = assertThrows(RuntimeException::class.java) {
      repository.save(domain[0])
    }
    assertEquals("something was wrong", ex.message)
  }

  @Test
  fun `when save and dao is called to save`() {
    buildEntitiesAndDomain()
    val expected = domain[0]
    repository.save(expected)
    verify(dao).insert(argThat { it ->
      it.date == expected.date && it.duration ==
          expected.preachingDuration
    })
  }
}


