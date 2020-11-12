package com.dora2000.themoviedb.NowPlayingapi

import com.dora2000.themoviedb.model.NowPlayingMovies
import com.dora2000.themoviedb.model.SearchMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NowPlayingInterface {
    @GET("now_playing")
    fun getNowPlaying(
        @Query("api_key") api_key: String
    ):Call<NowPlayingMovies>
}