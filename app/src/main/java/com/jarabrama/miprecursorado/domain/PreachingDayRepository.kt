package com.jarabrama.miprecursorado.domain

import com.jarabrama.miprecursorado.domain.model.PreachingDay
import java.time.Duration
import java.util.Date

interface PreachingDayRepository {

  fun findByYearAndMoth(year: Int, month: Int): List<PreachingDay>

  fun findByDate(date: Date): PreachingDay?

  fun updateDuration(date: Date, duration: Duration)

  fun save(preachingDay: PreachingDay)
}