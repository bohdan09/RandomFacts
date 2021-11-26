package com.example.randomfacts.data.repository

import androidx.lifecycle.LiveData
import com.example.randomfacts.data.data.source.local.LocalDataSource
import com.example.randomfacts.domain.model.NumbersFact

class HistoryRepository(
    private val localDataSource: LocalDataSource
) {
    fun getFactByNumber(number: Int): LiveData<NumbersFact> {
        return localDataSource.getFactByNumber(number = number)
    }

    fun insertNumberFact(numbersFact: NumbersFact) {
        localDataSource.insertNumberFact(numbersFact = numbersFact)
    }

    fun getAllNumberFacts(): LiveData<List<NumbersFact>> {
        return localDataSource.getAllNumberFacts()
    }
}