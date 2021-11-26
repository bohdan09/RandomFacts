package com.example.randomfacts.domain.usecase

import com.example.randomfacts.data.repository.HistoryRepository
import com.example.randomfacts.domain.model.NumbersFact

class GetFactByNumberUseCase(
    val historyRepository: HistoryRepository
) {
    fun execute(number: Int): NumbersFact {
        return historyRepository.getFactByNumber(number = number)
    }
}