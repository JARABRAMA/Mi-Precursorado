package com.jarabrama.miprecursorado.domain.model

import java.time.Duration
import java.util.Date
import java.util.UUID

data class PreachingDay(
  val id: UUID,
  val date: Date,
  val preachingDuration: Duration,
)