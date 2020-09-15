package com.example.get_api.ApiClient

import com.example.get_api.Interface.ApiInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HomeApiClient {
    var BASE_URL : String ="https://www.u-learn.in/v1/"

    val getClientData : ApiInterface
        get() {
            val gson : Gson = GsonBuilder()
            .setLenient()
                .create()
        val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client : OkHttpClient= OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit= Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).client(client).build()
            return retrofit.create(ApiInterface::class.java)
        }
}