package com.jarabrama.miprecursorado.presistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jarabrama.miprecursorado.presistence.entities.PreachingDayEntity
import java.time.LocalDate
import java.time.Duration
import java.time.LocalDateTime

@Dao
interface PreachingDayDao {

  @Query(
    """
    SELECT * FROM preaching_days
    WHERE strftime('%Y', date) = :year
    AND strftime('%m', date) = :month
  """
  )
  fun findPreachingDayByYearAndMonth(year: Int, month: Int): List<PreachingDayEntity>;

  @Query("SELECT * FROM preaching_days WHERE date = :date")
  fun findByDate(date: LocalDate): PreachingDayEntity?

  @Insert
  fun insert(preachingDay: PreachingDayEntity)

  @Query("UPDATE preaching_days SET duration = :duration WHERE date = :date")
  fun updateDuration(date: LocalDate, duration: Duration);

  @Query("UPDATE preaching_days SET updatedAt = :updateDate WHERE date = :date")
  fun updateUpdatedAt(date: LocalDate, updateDate: LocalDateTime);
}