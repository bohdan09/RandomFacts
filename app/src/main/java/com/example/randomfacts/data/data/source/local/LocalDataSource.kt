package com.example.randomfacts.data.data.source.local

import androidx.lifecycle.LiveData
import com.example.randomfacts.domain.model.NumbersFact

interface LocalDataSource {
    fun getFactByNumber(number: Int): NumbersFact
    fun insertNumberFact(numbersFact: NumbersFact)
    fun getAllNumberFacts(): LiveData<List<NumbersFact>>
}