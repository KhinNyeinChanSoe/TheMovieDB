package com.dora2000.themoviedb.upcomingApi

import com.dora2000.themoviedb.model.Upcoming
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpcomingApiClient {

    private val BASE_URL = "https://api.themoviedb.org/3/movie/"
    var upcomingApiInterface: UpcomingApiInterface

    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        upcomingApiInterface = retrofit.create(
            UpcomingApiInterface::class.java
        )
    }

    fun getUpcoming(apiKey: String): Call<Upcoming> {
        return upcomingApiInterface.getUpcoming("e63fc1745d3af612e3f5e042a1b0e14b")
    }

}