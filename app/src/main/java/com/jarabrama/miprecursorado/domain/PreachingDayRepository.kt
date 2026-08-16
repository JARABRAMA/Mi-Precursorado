package com.jarabrama.miprecursorado.domain

import com.jarabrama.miprecursorado.domain.model.PreachingDay
import java.time.Duration
import java.time.LocalDate

interface PreachingDayRepository {

  fun findByYearAndMoth(year: Int, month: Int): List<PreachingDay>

  fun findByDate(date: LocalDate): PreachingDay?

  fun updateDuration(date: LocalDate, duration: Duration)

  fun save(preachingDay: PreachingDay)
}