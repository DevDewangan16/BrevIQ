package com.example.gemi.ui

import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ImageToTextScreen(brevIQViewModel: BrevIQViewModel, apiKey: String) {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display Image if Selected
        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Selected Image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to Select Image
        Button(onClick = { launcher.launch("image/*") }) {
            Text("Pick an Image")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Button to Process Image
        Button(
            onClick = {
                bitmap?.let { brevIQViewModel.processImage(it, apiKey) }
            },
            enabled = bitmap != null
        ) {
            Text("Extract Text")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display Extracted Text
        Text(
            text = brevIQViewModel.extractedText.value,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )
    }
}
