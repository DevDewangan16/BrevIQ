package com.example.gemi.ui

import Content1
import GeminiImageRequest
import GeminiImageResponse
import InlineData
import Part1
import android.app.Application
import android.graphics.Bitmap
import android.net.http.HttpException
import android.os.Build
import android.telecom.Call
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gemi.ui.ImageUtils.convertBitmapToBase64
import com.example.gemi.ui.data.Content
import com.example.gemi.ui.data.GeminiRequest
import com.example.gemi.ui.data.Part
import com.google.android.gms.common.api.Response
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.security.auth.callback.Callback

class BrevIQViewModel(application:Application):AndroidViewModel(application) {

    private val apiKey = "AIzaSyDbGhLvg47UU1tY7O0LS7dbeho1dFEuvPk"

    private val _response = MutableStateFlow("Ask something...")
    val response = _response.asStateFlow()

    fun fetchResponse(prompt: String) {
        viewModelScope.launch {
            try {
                val request = GeminiRequest(
                    contents = listOf(
                        Content(parts = listOf(Part(text = prompt)))  // ✅ Correct format
                    )
                )

                val result = RetrofitClient.instance.getGeminiResponse(apiKey, request)

                // ✅ Correctly extracting response text
                val responseText = result.candidates?.getOrNull(0)?.content?.parts?.getOrNull(0)?.text ?: "No response"

                _response.value = responseText

            } catch (e: Exception) {
                _response.value = "Error: ${e.message}"
            }
        }
    }

    var extractedText = mutableStateOf("No response yet")

    fun processImage(bitmap: Bitmap, apiKey: String) {
        val base64Image = ImageUtils.convertBitmapToBase64(bitmap)

        val request = GeminiImageRequest(
            contents = listOf(
                Content1(
                    parts = listOf(
                        Part1(
                            inlineData = InlineData(
                                mimeType = "image/png",
                                data = base64Image
                            )
                        )
                    )
                )
            )
        )

        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient1.instance.processImage(apiKey, request)
                }

                extractedText.value = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
                    ?: "No text extracted"

            } catch (e: Exception) {
                extractedText.value = "Error: ${e.message}"
            }
        }
    }

    val _isvisible=MutableStateFlow<Boolean>(true)
    val isvisible=_isvisible

    fun toggleVisibility(){
        isvisible.value=false
    }

    private val _user=MutableStateFlow<FirebaseUser?>(null)
    val user:MutableStateFlow<FirebaseUser?>get() = _user

    fun setUser(user:FirebaseUser){
        _user.value=user
    }

    private val _email=MutableStateFlow<String>("")
    val email:MutableStateFlow<String>get() =_email

    private val _password=MutableStateFlow<String>("")
    val password:MutableStateFlow<String>get() = _password

    private val _name=MutableStateFlow<String>("")
    val name :MutableStateFlow<String> get()=_name

    fun setEmail(email:String){
        _email.value=email
    }

    fun setPassword(password:String){
        _password.value=password
    }

    fun setName(name:String){
        _name.value=name
    }
    init {
        viewModelScope.launch(Dispatchers.Default) {
            delay(3000)
            toggleVisibility()
        }
    }
}