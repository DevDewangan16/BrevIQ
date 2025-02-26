package com.example.gemi.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
class BrevIQViewModel(application:Application):AndroidViewModel(application) {
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