package com.example.fampayapp.networking

import com.example.fampayapp.model.CardGroup
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIInterface{

    @GET
    fun getCardData(@Url url: String) : Call<CardGroup>

}