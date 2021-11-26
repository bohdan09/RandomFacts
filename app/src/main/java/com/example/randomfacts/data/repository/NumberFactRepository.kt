package com.example.randomfacts.data.repository

import com.example.randomfacts.data.data.source.remote.RemoteDataSourceImpl
import com.example.randomfacts.domain.model.NumbersFact
import retrofit2.Call

class NumberFactRepository(
    private val dataSourceImpl: RemoteDataSourceImpl
) {
    fun getRandomFactByRandomNumber(): Call<NumbersFact> {
        return dataSourceImpl.getRandomFactByRandomNumber()
    }

    fun getFactByUserNumber(number: Int): Call<NumbersFact> {
        return dataSourceImpl.getRandomFactByUserNumber(number = number)
    }
}