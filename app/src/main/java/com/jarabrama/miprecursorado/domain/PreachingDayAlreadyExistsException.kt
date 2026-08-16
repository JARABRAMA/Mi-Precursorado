package com.jarabrama.miprecursorado.domain

class PreachingDayAlreadyExistsException: RuntimeException{
  constructor(message: String): super(message)
}