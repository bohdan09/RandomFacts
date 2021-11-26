package com.example.randomfacts.data.api

import com.example.randomfacts.domain.model.NumbersFact
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/random/math")
    fun getRandomFactByRandomNumber(@Query("json") type: String = "json"): Call<NumbersFact>

    @GET("/{number}")
    fun getRandomFactByUserNumber(
        @Path("number") number: Int,
        @Query("json") type: String = "json"
    ): Call<NumbersFact>
}