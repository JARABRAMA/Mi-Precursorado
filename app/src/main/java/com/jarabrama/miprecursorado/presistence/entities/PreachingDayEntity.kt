package com.jarabrama.miprecursorado.presistence.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Entity(
  tableName = "preaching_days",
  indices = [Index(value = ["date"], unique = true)]
)
data class PreachingDayEntity(
  @PrimaryKey(autoGenerate = true)
  val id: UUID = UUID.randomUUID(),
  val date: LocalDate,
  val duration: Duration,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime
)