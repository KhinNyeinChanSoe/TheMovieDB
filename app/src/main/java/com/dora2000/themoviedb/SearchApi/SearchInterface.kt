package com.dora2000.themoviedb.SearchApi

import com.dora2000.themoviedb.model.SearchMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchInterface {
    @GET("movie")
    fun getSearch(
        @Query("query")keyword :String,
        @Query("api_key")api_key:String
    ):Call<SearchMovie>
}