package com.example.gemi.ui

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

//used for converting the bitmap means the image format to the base means the string format
object ImageUtils {
    fun convertBitmapToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }
}
