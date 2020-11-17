package com.dora2000.themoviedb.upcomingApi

import com.dora2000.themoviedb.model.Upcoming
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpcomingApiInterface {

    @GET("upcoming")
    fun getUpcoming(
        @Query("api_key") api_key: String
    ): Call<Upcoming>

}