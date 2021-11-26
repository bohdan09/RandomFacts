package com.example.randomfacts.data.data.source.remote

import com.example.randomfacts.data.api.ApiService
import com.example.randomfacts.domain.model.NumbersFact
import retrofit2.Call

class RemoteDataSourceImpl(
    private val apiService: ApiService
) : RemoteDataSource {
    override fun getRandomFactByRandomNumber(): Call<NumbersFact> {
        return apiService.getRandomFactByRandomNumber()
    }

    override fun getRandomFactByUserNumber(number: Int): Call<NumbersFact> {
        return apiService.getRandomFactByUserNumber(number = number)
    }

}