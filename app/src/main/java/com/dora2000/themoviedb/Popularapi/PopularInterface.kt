package com.dora2000.themoviedb.Popularapi

import com.dora2000.themoviedb.model.PopularMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularInterface {
    @GET("popular")
    fun getPopular(
        @Query("api_key")api_key:String
    ):Call<PopularMovies>
}