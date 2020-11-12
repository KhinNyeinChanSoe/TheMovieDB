package com.dora2000.themoviedb.ui.now_playing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dora2000.themoviedb.NowPlayingapi.NowPlayingApiClient
import com.dora2000.themoviedb.model.NowPlayingMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingViewModel : ViewModel() {

    var apiClient = NowPlayingApiClient()
    private var result: MutableLiveData<NowPlayingMovies> = MutableLiveData()
    private var errorStatus: MutableLiveData<Boolean> = MutableLiveData()
    private var errorMessage: MutableLiveData<String> = MutableLiveData()
    private var isloading: MutableLiveData<Boolean> = MutableLiveData()

    fun getResult(): LiveData<NowPlayingMovies> = result
    fun getErrorStatus(): LiveData<Boolean> = errorStatus
    fun getErrorMessage(): LiveData<String> = errorMessage
    fun getIsLoading(): LiveData<Boolean> = isloading
    fun loadResult() {
        var apiCall = apiClient.getNowPlaying("e63fc1745d3af612e3f5e042a1b0e14b")
        apiCall.enqueue(object : Callback<NowPlayingMovies> {
            override fun onResponse(
                call: Call<NowPlayingMovies>,
                response: Response<NowPlayingMovies>
            ) {
                isloading.value = false
                errorStatus.value = false
                result.value = response.body()
            }

            override fun onFailure(call: Call<NowPlayingMovies>, t: Throwable) {
                isloading.value = false
                errorStatus.value = true
                errorMessage.value = t.toString()
            }

        })
    }



}