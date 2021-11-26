package com.example.randomfacts.data.data.source.local

import androidx.lifecycle.LiveData
import com.example.randomfacts.data.room.HistoryFactsDao
import com.example.randomfacts.domain.model.NumbersFact


class LocalDataSourceImpl(
    val historyFactsDao: HistoryFactsDao
) : LocalDataSource {
    override fun getFactByNumber(number: Int): LiveData<NumbersFact> {
        return historyFactsDao.getHistoryRecordByNumber(number = number)
    }

    override fun insertNumberFact(numbersFact: NumbersFact) {
        historyFactsDao.addNumberFact(fact = numbersFact)
    }

    override fun getAllNumberFacts(): LiveData<List<NumbersFact>> {
        return historyFactsDao.addAllHistory()
    }

}