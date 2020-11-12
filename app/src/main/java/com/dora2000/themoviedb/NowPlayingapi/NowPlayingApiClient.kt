package com.dora2000.themoviedb.NowPlayingapi

import com.dora2000.themoviedb.model.NowPlayingMovies
import com.dora2000.themoviedb.model.SearchMovie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NowPlayingApiClient {
    private val BASE_URL = "https://api.themoviedb.org/3/movie/"
    var nowplayapiInterface: NowPlayingInterface

    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        nowplayapiInterface = retrofit.create(
            NowPlayingInterface::class.java
        )
    }

    fun getNowPlaying(apiKey: String): Call<NowPlayingMovies> {
        return nowplayapiInterface.getNowPlaying("e63fc1745d3af612e3f5e042a1b0e14b")
    }

}