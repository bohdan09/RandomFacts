package com.example.randomfacts.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.randomfacts.domain.model.NumbersFact

@Dao
interface HistoryFactsDao {
    @Insert
    fun addNumberFact(fact: NumbersFact)

    @Query("SELECT * FROM ${AppDB.TABLE_NAME}")
    fun addAllHistory(): LiveData<List<NumbersFact>>

    @Query("SELECT * FROM ${AppDB.TABLE_NAME} WHERE number = :number")
    fun getHistoryRecordByNumber(number: Int): NumbersFact

}