package com.example.randomfacts.domain.usecase

import androidx.lifecycle.LiveData
import com.example.randomfacts.data.repository.HistoryRepository
import com.example.randomfacts.domain.model.NumbersFact

class GetFactByNumberUseCase(
    val historyRepository: HistoryRepository
) {
    fun execute(number: Int): LiveData<NumbersFact> {
        return historyRepository.getFactByNumber(number = number)
    }
}