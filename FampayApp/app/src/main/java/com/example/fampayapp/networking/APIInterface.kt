package com.example.fampayapp.networking

import com.example.fampayapp.model.APIResponse
import com.example.fampayapp.model.CardGroup
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIInterface{

    @GET("fefcfbeb-5c12-4722-94ad-b8f92caad1ad")
    fun getCardData() : Call<APIResponse>

}