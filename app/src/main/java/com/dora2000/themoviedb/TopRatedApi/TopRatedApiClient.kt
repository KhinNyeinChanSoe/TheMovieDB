package com.dora2000.themoviedb.TopRatedApi

import com.dora2000.themoviedb.model.TopRated
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TopRatedApiClient {

    private val BASE_URL = "https://api.themoviedb.org/3/movie/"
    var topRatedApiInterface: TopRatedApiInterface

    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        topRatedApiInterface = retrofit.create(
            TopRatedApiInterface::class.java
        )
    }

    fun getTopRated(apiKey: String): Call<TopRated> {
        return topRatedApiInterface.getTopRated("e63fc1745d3af612e3f5e042a1b0e14b")
    }

}