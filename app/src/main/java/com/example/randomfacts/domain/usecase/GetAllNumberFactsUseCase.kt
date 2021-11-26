package com.example.randomfacts.domain.usecase

import androidx.lifecycle.LiveData
import com.example.randomfacts.data.repository.HistoryRepository
import com.example.randomfacts.domain.model.NumbersFact

class GetAllNumberFactsUseCase(
    val historyRepository: HistoryRepository
) {
    fun execute(): LiveData<List<NumbersFact>> {
        return historyRepository.getAllNumberFacts()
    }
}