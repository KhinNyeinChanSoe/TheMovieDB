package com.dora2000.themoviedb.TopRatedApi

import com.dora2000.themoviedb.model.TopRated
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedApiInterface {

    @GET("top_rated")
    fun getTopRated(
        @Query("api_key") api_key: String
    ): Call<TopRated>

}