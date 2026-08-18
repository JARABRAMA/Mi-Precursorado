package com.jarabrama.miprecursorado.presistence

import android.os.Build
import androidx.annotation.RequiresApi
import com.jarabrama.miprecursorado.domain.PreachingDayRepository
import com.jarabrama.miprecursorado.domain.model.PreachingDay
import com.jarabrama.miprecursorado.presistence.dao.PreachingDayDao
import com.jarabrama.miprecursorado.presistence.entities.PreachingDayEntity
import com.jarabrama.miprecursorado.presistence.mappers.PreachingDayEntityMapper
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

class PreachingDayRepositoryRoomImpl(
  private val dao: PreachingDayDao,
  private val mapper: PreachingDayEntityMapper
) : PreachingDayRepository {

  override fun findByYearAndMoth(
    year: Int,
    month: Int
  ): List<PreachingDay> {
    val entities = dao.findPreachingDayByYearAndMonth(year, month)
    return entities.map(mapper::toDomain)
  }

  override fun findByDate(date: LocalDate): PreachingDay? =
    dao.findByDate(date)?.let { mapper.toDomain(it) }

  @RequiresApi(Build.VERSION_CODES.O)
  override fun updateDuration(date: LocalDate, duration: Duration) {
    dao.updateDuration(date = date, duration = duration)
    dao.updateUpdatedAt(date = date, updateDate = LocalDate.now())
  }

  @RequiresApi(Build.VERSION_CODES.O)
  override fun save(preachingDay: PreachingDay) {
    val now = LocalDateTime.now()
    val entity = PreachingDayEntity(
      id= UUID.randomUUID(),
      date = preachingDay.date,
      duration = preachingDay.preachingDuration,
      createdAt = now,
      updatedAt = now
    )
  dao.insert(entity)
  }
}