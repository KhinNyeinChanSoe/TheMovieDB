package com.dora2000.themoviedb.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dora2000.themoviedb.model.Upcoming
import com.dora2000.themoviedb.upcomingApi.UpcomingApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingViewModel : ViewModel() {

    var apiClient = UpcomingApiClient()
    private var result: MutableLiveData<Upcoming> = MutableLiveData()
    private var errorStatus: MutableLiveData<Boolean> = MutableLiveData()
    private var errorMessage: MutableLiveData<String> = MutableLiveData()
    private var isloading: MutableLiveData<Boolean> = MutableLiveData()

    fun getResult(): LiveData<Upcoming> = result
    fun getErrorStatus(): LiveData<Boolean> = errorStatus
    fun getErrorMessage(): LiveData<String> = errorMessage
    fun getIsLoading(): LiveData<Boolean> = isloading
    fun loadResult() {
        var apiCall = apiClient.getUpcoming("e63fc1745d3af612e3f5e042a1b0e14b")
        apiCall.enqueue(object : Callback<Upcoming> {
            override fun onResponse(
                call: Call<Upcoming>,
                response: Response<Upcoming>
            ) {
                isloading.value = false
                errorStatus.value = false
                result.value = response.body()
            }

            override fun onFailure(call: Call<Upcoming>, t: Throwable) {
                isloading.value = false
                errorStatus.value = true
                errorMessage.value = t.toString()
            }

        })
    }



}