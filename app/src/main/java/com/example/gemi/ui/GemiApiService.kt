package com.example.gemi.ui

import GeminiImageRequest
import GeminiImageResponse
import android.telecom.Call
import com.example.gemi.ui.data.GeminiRequest
import com.example.gemi.ui.data.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GemiApiService {
    @Headers("Content-Type: application/json")
    @POST("v1beta/models/gemini-1.5-flash:generateContent")
    suspend fun getGeminiResponse(
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}

