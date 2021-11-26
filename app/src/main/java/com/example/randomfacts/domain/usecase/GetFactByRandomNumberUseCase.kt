package com.example.randomfacts.domain.usecase

import com.example.randomfacts.domain.model.NumbersFact
import com.example.randomfacts.data.repository.NumberFactRepository
import retrofit2.Call

class GetFactByRandomNumberUseCase(
   private val numberFactRepository: NumberFactRepository
) {
    fun execute() : Call<NumbersFact> {
        return numberFactRepository.getRandomFactByRandomNumber()
    }
}