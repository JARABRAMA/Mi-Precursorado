package com.jarabrama.miprecursorado.presistence.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration
import java.util.Date
import java.util.UUID

@Entity(tableName = "preaching_days")
data class PreachingDayEntity (
  @PrimaryKey(autoGenerate = true)
  val id: UUID = UUID.randomUUID(),
  val date: Date,
  val duration: Duration
)