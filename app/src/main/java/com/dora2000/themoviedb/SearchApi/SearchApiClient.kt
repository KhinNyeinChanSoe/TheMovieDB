package com.dora2000.themoviedb.SearchApi

import com.dora2000.themoviedb.model.SearchMovie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class SearchApiClient {
    private val BASE_URL = "https://api.themoviedb.org/3/search/"
    val searchInterface:SearchInterface
    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        searchInterface = retrofit.create(SearchInterface::class.java)
    }
    fun getSearch(keyword:String,apiKey:String): Call<SearchMovie> {
        return searchInterface.getSearch("movie","e63fc1745d3af612e3f5e042a1b0e14b")
    }
}