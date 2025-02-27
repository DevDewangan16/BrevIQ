package com.example.gemi.ui

import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
            .fillMaxSize(),
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .height(400.dp)
                    .weight(1f) // ✅ Ensures TextField stays at the bottom even when empty
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Text(
                            text = brevIQViewModel.extractedText.value,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(8.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .padding(start = 3.dp, end = 3.dp, bottom = 40.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                        launcher.launch("image/*")
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFCCE5E3)
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                  //  horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(modifier = Modifier.weight(3.5f).fillMaxHeight(),
                        contentAlignment = Alignment.Center
                        ) {
                        Text(
                            text = "Upload an Image",
                            textAlign = TextAlign.Center
                        )
                    }
                    IconButton(
                        modifier = Modifier.weight(0.5f),
                        onClick = {
                            bitmap?.let { brevIQViewModel.processImage(it, apiKey) }
                        },
                        enabled = bitmap != null
                    ) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = " ")
                    }
                }
            }
        }

    }
    Spacer(modifier = Modifier.height(16.dp))
}
