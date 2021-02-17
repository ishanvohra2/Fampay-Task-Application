package com.example.fampayapp.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fampayapp.model.APIResponse
import com.example.fampayapp.model.CardGroup
import com.example.fampayapp.networking.APIInterface
import com.example.fampayapp.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository{

    fun getCardGroups() : MutableLiveData<APIResponse>{
        val mutableLiveData: MutableLiveData<APIResponse> = MutableLiveData()

        val call = RetrofitClient.apiInterface.getCardData()
        call.enqueue(object: Callback<APIResponse> {
            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                Log.d("Repository", "Get card groups failed: " + t.message)
                mutableLiveData.value = null
            }

            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.isSuccessful){
                    mutableLiveData.value = response.body()
                }
                else mutableLiveData.value = null

                Log.d("Repository", "Get card group: " + response.code() + " " + response.message())
            }
        })

        return mutableLiveData
    }

}