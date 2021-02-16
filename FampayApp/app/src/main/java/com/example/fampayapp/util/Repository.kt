package com.example.fampayapp.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fampayapp.model.CardGroup
import com.example.fampayapp.networking.APIInterface
import com.example.fampayapp.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository{

    fun getCardGroups(url: String) : MutableLiveData<CardGroup>{
        lateinit var mutableLiveData: MutableLiveData<CardGroup>

        val call = RetrofitClient.apiInterface.getCardData(url)
        call.enqueue(object: Callback<CardGroup> {
            override fun onFailure(call: Call<CardGroup>, t: Throwable) {
                Log.d("Repository", "onResponse: " + t.message)
                mutableLiveData.value = null
            }

            override fun onResponse(call: Call<CardGroup>, response: Response<CardGroup>) {
                if (response.isSuccessful){
                    mutableLiveData.value = response.body()
                }
                else mutableLiveData.value = null

                Log.d("Repository", "onResponse: " + response.code() + " " + response.message())
            }
        })

        return mutableLiveData
    }

}