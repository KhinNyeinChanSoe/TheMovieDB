package com.dora2000.themoviedb.ui.top_rated

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dora2000.themoviedb.TopRatedApi.TopRatedApiClient
import com.dora2000.themoviedb.model.TopRated
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedViewModel : ViewModel() {
    var apiClient = TopRatedApiClient()
    private var top_rated_result:MutableLiveData<TopRated> = MutableLiveData()
    private var top_rated_error_status : MutableLiveData<Boolean> = MutableLiveData()
    private var top_rated_error_message : MutableLiveData<String> = MutableLiveData()
    private var top_rated_isLoading : MutableLiveData<Boolean> = MutableLiveData()

    fun getTopRatedResult():LiveData<TopRated> = top_rated_result
    fun getTopRatedErrorStatus():LiveData<Boolean> = top_rated_error_status
    fun getTopRatedErrorMessage():LiveData<String> = top_rated_error_message
    fun getTopRatedIsLoading():LiveData<Boolean> = top_rated_isLoading

    fun loadTopRatedResult(){
        var apiCall = apiClient.getTopRated("e63fc1745d3af612e3f5e042a1b0e14b")
        apiCall.enqueue(object :Callback<TopRated>{
            override fun onResponse(call: Call<TopRated>, response: Response<TopRated>) {
                top_rated_isLoading.value = false
                top_rated_error_status.value = false
                top_rated_result.value = response.body()
                Log.d("RESULT>>>>>>>>>",response.body().toString())
            }

            override fun onFailure(call: Call<TopRated>, t: Throwable) {
                top_rated_isLoading.value = false
                top_rated_error_status.value = false
                top_rated_error_message.value = t.toString()
            }

        })
    }

}