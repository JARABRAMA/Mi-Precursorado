package com.jarabrama.miprecursorado.usecases

import com.jarabrama.miprecursorado.domain.PreachingDayRepository
import com.jarabrama.miprecursorado.domain.model.PreachingDay

class FindPreachingDaysUseCase (private val repository: PreachingDayRepository){

  fun execute(year: Int, month: Int): List<PreachingDay> {
    return repository.findByYearAndMoth(year, month)
  }
}