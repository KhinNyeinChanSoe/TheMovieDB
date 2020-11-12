package com.dora2000.themoviedb.ui.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dora2000.themoviedb.Popularapi.PopularApiClient
import com.dora2000.themoviedb.Popularapi.PopularInterface
import com.dora2000.themoviedb.model.NowPlayingMovies
import com.dora2000.themoviedb.model.PopularMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularViewModel : ViewModel() {
    var apiClient = PopularApiClient()
    private var popular_result:MutableLiveData<PopularMovies> = MutableLiveData()
    private var popular_error_status : MutableLiveData<Boolean> = MutableLiveData()
    private var popular_error_message : MutableLiveData<String> = MutableLiveData()
    private var popular_isLoading : MutableLiveData<Boolean> = MutableLiveData()

    fun getPopularResult():LiveData<PopularMovies> = popular_result
    fun getPopularErrorStatus():LiveData<Boolean> = popular_error_status
    fun getPopularErrorMessage():LiveData<String> = popular_error_message
    fun getPopularIsLoading():LiveData<Boolean> = popular_isLoading

    fun loadPopularResult(){
        var apiCall = apiClient.getPopular("e63fc1745d3af612e3f5e042a1b0e14b")
        apiCall.enqueue(object :Callback<PopularMovies>{
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                popular_isLoading.value = false
                popular_error_status.value = false
                popular_result.value = response.body()
                Log.d("RESULT>>>>>>>>>",response.body().toString())
            }

            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                popular_isLoading.value = false
                popular_error_status.value = false
                popular_error_message.value = t.toString()
            }

        })
    }

}