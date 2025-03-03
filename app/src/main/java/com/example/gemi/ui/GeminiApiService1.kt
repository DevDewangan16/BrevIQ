package com.example.gemi.ui

import GeminiImageRequest
import GeminiImageResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

// This Service is used for the Image to Text Based Response
interface GeminiApiService1 {
    @POST("v1beta/models/gemini-1.5-pro:generateContent")
    suspend fun processImage(
        @Query("key") apiKey: String,
        @Body request: GeminiImageRequest
    ): GeminiImageResponse
}