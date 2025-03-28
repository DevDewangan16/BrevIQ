package com.example.gemi.ui

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// This is for the text to text response
object RetrofitClient {
    private const val BASE_URL = "https://generativelanguage.googleapis.com/"

    val instance: GemiApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(GemiApiService::class.java)
    }
}