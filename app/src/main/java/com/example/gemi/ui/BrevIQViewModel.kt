package com.example.gemi.ui

import android.app.Application
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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


    val _isvisible=MutableStateFlow<Boolean>(true)
    val isvisible=_isvisible

    private val _name=MutableStateFlow<String>("")
    val name :MutableStateFlow<String> get()=_name

    fun toggleVisibility(){
        isvisible.value=false
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