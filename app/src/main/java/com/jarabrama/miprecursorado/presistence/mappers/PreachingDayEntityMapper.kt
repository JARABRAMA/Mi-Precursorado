package com.jarabrama.miprecursorado.presistence.mappers

import com.jarabrama.miprecursorado.domain.model.PreachingDay
import com.jarabrama.miprecursorado.presistence.entities.PreachingDayEntity

class PreachingDayEntityMapper {

  fun toDomain(entity: PreachingDayEntity): PreachingDay {
    return PreachingDay(id = entity.id, date = entity.date, preachingDuration = entity.duration)
  }
}