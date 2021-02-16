package com.example.fampayapp.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient{

    lateinit var retrofitClient: Retrofit
    val baseUrl: String =""

    fun RetrofitClient(){
        if(retrofitClient != null){
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    fun provideOkHttpClient() : OkHttpClient{
        val okHttpClientBuilder : OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(100, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(100, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(100, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }

    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofitClient.create(serviceClass)
    }

}