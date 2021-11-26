package com.example.randomfacts.domain.usecase

import com.example.randomfacts.data.repository.HistoryRepository
import com.example.randomfacts.domain.model.NumbersFact

class InsertNumberFactToDbUseCase(
    val historyRepository: HistoryRepository
) {
    fun execute(numbersFact: NumbersFact) {
        historyRepository.insertNumberFact(numbersFact = numbersFact)
    }
}