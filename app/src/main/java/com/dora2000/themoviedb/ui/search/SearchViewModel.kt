package com.dora2000.themoviedb.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dora2000.themoviedb.SearchApi.SearchApiClient
import com.dora2000.themoviedb.model.PopularMovies
import com.dora2000.themoviedb.model.SearchMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    var searchApiClient = SearchApiClient()
    private var search_result: MutableLiveData<SearchMovie> = MutableLiveData()
    private var search_error_status: MutableLiveData<Boolean> = MutableLiveData()
    private var search_error_message: MutableLiveData<String> = MutableLiveData()
//    private var search_isLoading: MutableLiveData<Boolean> = MutableLiveData()
    fun getSearchResult(keyword:String) = search_result
    fun getSearchArticle(): MutableLiveData<SearchMovie> = search_result
    fun getSearchErrorStatus() = search_error_status
    fun getSearchErrorMessage() = search_error_message
//    fun getSearchIsLoading() = search_isLoading
    fun loadSearchResult(keyword: String) {
        var apiCall = searchApiClient.getSearch(keyword,"e63fc1745d3af612e3f5e042a1b0e14b")
        apiCall.enqueue(object : Callback<SearchMovie> {
            override fun onResponse(call: Call<SearchMovie>, response: Response<SearchMovie>) {
//                search_isLoading.value = false
                search_error_status.value = false
                search_result.value = response.body()
                Log.d("RESPONSE>>>>>>",response.body().toString())
            }

            override fun onFailure(call: Call<SearchMovie>, t: Throwable) {
//                search_isLoading.value = false
                search_error_status.value = false
                search_error_message.value = t.toString()
            }

        })

    }

}