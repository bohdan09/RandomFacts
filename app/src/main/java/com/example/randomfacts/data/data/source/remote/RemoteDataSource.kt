package com.example.randomfacts.data.data.source.remote

import com.example.randomfacts.domain.model.NumbersFact
import retrofit2.Call

interface RemoteDataSource {
    fun getRandomFactByRandomNumber(): Call<NumbersFact>

    fun getRandomFactByUserNumber(number: Int): Call<NumbersFact>
}