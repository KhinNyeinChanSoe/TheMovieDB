package com.dora2000.themoviedb.Popularapi

import com.dora2000.themoviedb.model.PopularMovies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PopularApiClient {
    private var BASE_URL = "https://api.themoviedb.org/3/movie/"
    var popularInterface:PopularInterface
    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        popularInterface = retrofit.create(PopularInterface::class.java)
    }
    fun getPopular(apiKey:String):Call<PopularMovies>{
        return popularInterface.getPopular("e63fc1745d3af612e3f5e042a1b0e14b")
    }
}