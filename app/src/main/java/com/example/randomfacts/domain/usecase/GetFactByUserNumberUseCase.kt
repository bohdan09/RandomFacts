package com.example.randomfacts.domain.usecase

import com.example.randomfacts.data.repository.NumberFactRepository
import com.example.randomfacts.domain.model.NumbersFact
import retrofit2.Call

class GetFactByUserNumberUseCase(
    val numberFactRepository: NumberFactRepository
) {
    fun execute(number: Int): Call<NumbersFact> {
        return numberFactRepository.getFactByUserNumber(number = number)
    }
}