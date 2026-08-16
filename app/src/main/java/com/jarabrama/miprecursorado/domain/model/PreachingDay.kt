package com.jarabrama.miprecursorado.domain.model

import java.time.Duration
import java.time.LocalDate

import java.util.UUID

data class PreachingDay(
  val id: UUID,
  val date: LocalDate,
  val preachingDuration: Duration,
)