package com.example.gemi.ui

import Content1
import GeminiImageRequest
import ImageMessage
import InlineData
import Part1
import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gemi.BuildConfig
import com.example.gemi.ui.data.ChatMessage
import com.example.gemi.ui.data.Content
import com.example.gemi.ui.data.DataBase
import com.example.gemi.ui.data.GeminiRequest
import com.example.gemi.ui.data.Part
import com.example.gemi.ui.data.RequestResponse
import com.example.gemi.ui.data.UserInfo
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class BrevIQViewModel(application:Application):AndroidViewModel(application) {

    private val apiKey = BuildConfig.GEMINI_API_KEY

    private val _response = MutableStateFlow("Ask something...")
    val response = _response.asStateFlow()

    private val _historyList = MutableStateFlow<List<RequestResponse>>(emptyList())
    val historyList: StateFlow<List<RequestResponse>> = _historyList

    private val _databaseList=MutableStateFlow<List<DataBase>>(emptyList())
    val databaseList:StateFlow<List<DataBase>>get() = _databaseList.asStateFlow()

    private val _chatHistory = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatHistory = _chatHistory.asStateFlow()

    private val _imageHistory=MutableStateFlow<List<ImageMessage>>(emptyList())
    val imageHistory=_imageHistory.asStateFlow()

    fun fetchResponse(prompt: String) {
        viewModelScope.launch {
            try {
                // Add the user's question to the chat history
                _chatHistory.value += ChatMessage(text = prompt, isQuestion = true)

                val request = GeminiRequest(
                    contents = listOf(
                        Content(parts = listOf(Part(text = prompt)))
                    )
                )

                val result = RetrofitClient.instance.getGeminiResponse(apiKey, request)

                // Extract the response text
                val responseText = result.candidates?.getOrNull(0)?.content?.parts?.getOrNull(0)?.text ?: "No response"

                // Add the response to the chat history
                _chatHistory.value += ChatMessage(text = responseText, isQuestion = false)

                //add in the history screen
                val newEntry = RequestResponse(prompt,responseText)
               _historyList.value = _historyList.value + newEntry

            } catch (e: Exception) {
                // Add the error message to the chat history
                _chatHistory.value += ChatMessage(text = "Error: ${e.message}", isQuestion = false)
            }
        }
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun processImage(bitmap: Bitmap, apiKey: String, userQuery: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _imageHistory.value += ImageMessage(text = userQuery, isQuestion = true)

                val base64Image = ImageUtils.convertBitmapToBase64(bitmap)

                val request = GeminiImageRequest(
                    contents = listOf(
                        Content1(
                            parts = listOf(
                                Part1(text = userQuery),
                                Part1(
                                    inlineData = InlineData(
                                        mimeType = "image/jpeg",
                                        data = base64Image
                                    )
                                )
                            )
                        )
                    )
                )

                val response = withContext(Dispatchers.IO) {
                    RetrofitClient1.instance.processImage(apiKey, request)
                }

                val responseText = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
                    ?: "No text extracted"
                _imageHistory.value += ImageMessage(text = responseText, isQuestion = false)
            } catch (e: Exception) {
                _imageHistory.value += ImageMessage(text = "Error: ${e.message}", isQuestion = false)
            } finally {
                _isLoading.value = false
            }
        }
    }


    //used for fetching the response with the help of Gemini of the Text to Text based
//    fun fetchResponse(prompt: String) {
//        viewModelScope.launch {
//            try {
//                val request = GeminiRequest(
//                    contents = listOf(
//                        Content(parts = listOf(Part(text = prompt)))  // ✅ Correct format
//                    )
//                )
//
//                val result = RetrofitClient.instance.getGeminiResponse(apiKey, request)
//
//                // ✅ Correctly extracting response text
//                val responseText = result.candidates?.getOrNull(0)?.content?.parts?.getOrNull(0)?.text ?: "No response"
//
//                _response.value = responseText
//
//                val newEntry = RequestResponse(prompt,responseText)
//                _historyList.value = _historyList.value + newEntry
//
//
//            } catch (e: Exception) {
//                _response.value = "Error: ${e.message}"
//            }
//        }
//    }

//    var extractedText = mutableStateOf("No response yet")
//
//    //used to fetch response with the help of Gemini of the Image to Text based
//    fun processImage(bitmap: Bitmap, apiKey: String) {
//        val base64Image = ImageUtils.convertBitmapToBase64(bitmap)
//
//        val request = GeminiImageRequest(
//            contents = listOf(
//                Content1(
//                    parts = listOf(
//                        Part1(
//                            inlineData = InlineData(
//                                mimeType = "image/png",
//                                data = base64Image
//                            )
//                        )
//                    )
//                )
//            )
//        )
//
//        viewModelScope.launch {
//            try {
//                val response = withContext(Dispatchers.IO) {
//                    RetrofitClient1.instance.processImage(apiKey, request)
//                }
//
//                extractedText.value = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
//                    ?: "No text extracted"
//
//            } catch (e: Exception) {
//                extractedText.value = "Error: ${e.message}"
//            }
//        }
//    }

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

    private val _logoutClicked=MutableStateFlow(false)
    val logoutClicked: StateFlow<Boolean>
        get() = _logoutClicked.asStateFlow()

    fun setLogoutStatus(
        logoutStatus:Boolean
    ){
        _logoutClicked.value=logoutStatus
    }

    fun clearData(){
        _user.value=null
        _name.value=""
        _email.value=""
        _password.value=""
    }

    val database= Firebase.database
    val myRef = database.getReference("users/${auth.currentUser?.uid}/cart")

    private val _userInfo=MutableStateFlow<UserInfo?>(null)
    val userInfo:MutableStateFlow<UserInfo?>get() = _userInfo
    val myRef2 = database.getReference("users/${auth.currentUser?.uid}/userDetails")

    fun addUserToDatabase(item:UserInfo){
        myRef2.push().setValue(item)
    }


    //for storing in the datastore and get the information while reopen the app
    private val Context.datastore : DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore("cart")
    private val cartItemsKey= stringPreferencesKey("cart_items")
    private val context=application.applicationContext

    lateinit var screenJob: Job
    lateinit var InternetJob:Job

    private suspend fun saveCartItemsToDataStore(){
        context.datastore.edit { preferences->
            preferences[cartItemsKey]= Json.encodeToString(_databaseList.value)
        }
    }

    private suspend fun loadCartItemsFromDataStore(){
        val fullData=context.datastore.data.first()
        val cartItemsJson=fullData[cartItemsKey]
        if (!cartItemsJson.isNullOrEmpty()){
            _databaseList.value= Json.decodeFromString(cartItemsJson)
        }
    }

    fun getSavedItems(){
        InternetJob=viewModelScope.launch {
            try {
                loadCartItemsFromDataStore()
            }catch (exception:Exception){
                toggleVisibility()
                screenJob.cancel()
            }
        }
    }

    fun addToCart(item:DataBase){
        _databaseList.value = _databaseList.value + item
        viewModelScope.launch {
            saveCartItemsToDataStore()
        }
    }

    fun addToDatabase(item:DataBase){
        myRef.push().setValue(item)
    }

    //getting data from the firebase and adding the saved information
    fun fillCartItems(){
        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                _databaseList.value= emptyList()
                for (childSnapshot in dataSnapshot.children){
                    val item=childSnapshot.getValue(DataBase::class.java)
                    item?.let {
//                        val newItem=it
//                        addToCart(newItem)
                        _databaseList.value = _databaseList.value + it

                    }
                }
//                setLoading(false)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    //removing the data from the saved screen and also database
    fun removeFromCart(oldItem:DataBase){
        /* _cartItems.value = _cartItems.value - item
         viewModelScope.launch {
             saveCartItemsToDataStore()
         }*/ //this code is for to remove item from the cart only not database
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //_databaseList.value= emptyList()
                for (childSnapshot in dataSnapshot.children){
                    var itemRemoved=false
                    val item=childSnapshot.getValue(DataBase::class.java)
                    item?.let {
                        if (oldItem.request ==it.request && oldItem.response == it.response){
                            childSnapshot.ref.removeValue()
                            itemRemoved=true
                        }
                    }
                    if(itemRemoved) break
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }


    //this block of code always executed when the user open or reopen the app
    init {
        screenJob=viewModelScope.launch(Dispatchers.Default) {
            delay(3000)
            toggleVisibility()
        }
        getSavedItems()
        fillCartItems()
    }

}