package com.example.gemi.ui

import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gemi.BuildConfig

@Composable
fun ImageToTextScreen(brevIQViewModel: BrevIQViewModel) {
    var userInput by remember { mutableStateOf(TextFieldValue("")) }
    val apiKey = BuildConfig.GEMINI_API_KEY
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }
    }

    val imageHistory by brevIQViewModel.imageHistory.collectAsState()
    val isLoading by brevIQViewModel.isLoading.collectAsState()

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
            // Chat History
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(imageHistory) { message ->
                        val alignment = if (message.isQuestion) Alignment.TopEnd else Alignment.TopStart
                        // val alignment = if (message.isQuestion) Alignment.End else Alignment.Start
                        val backgroundColor = if (message.isQuestion) Color(0xFFEEDEF6) else Color(0xFFDCF8C6)
                        val textColor = if (message.isQuestion) Color.Black else Color.Black

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            contentAlignment = alignment
                        ) {
                            MarkdownText(
                                markdown = message.text,
                                modifier = Modifier
                                    .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
            }

            // Loading Indicator
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            // Input Field
            OutlinedTextField(
                value = userInput,
                onValueChange = { userInput = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 17.dp),
                placeholder = { Text("Ask Gemini...") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFEEDEF6),
                    unfocusedContainerColor = Color(0xFFEEDEF6),
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    focusedTrailingIconColor = Color.Black,
                    unfocusedTrailingIconColor = Color.Black,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                leadingIcon = {
                    IconButton(onClick = {
                        launcher.launch("image/*")
                    }
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Upload")
                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            bitmap?.let { brevIQViewModel.processImage(it, apiKey, userInput.text) }
                            userInput = TextFieldValue("") // Clear the input field
                        },
                        enabled = bitmap != null && !isLoading // Disable button if no image is selected or while loading
                    ) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
                    }
                }
            )
        }
    }
//    val context = LocalContext.current
//    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
//    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
//        uri?.let {
//            bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Display Image if Selected
//        bitmap?.let {
//            Image(
//                bitmap = it.asImageBitmap(),
//                contentDescription = "Selected Image",
//                modifier = Modifier
//                    .size(200.dp)
//                    .clip(RoundedCornerShape(10.dp))
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(10.dp),
//            verticalArrangement = Arrangement.SpaceBetween
//        ) {
//            Box(
//                modifier = Modifier
//                    .height(400.dp)
//                    .weight(1f) // ✅ Ensures TextField stays at the bottom even when empty
//                    .fillMaxWidth()
//                    .padding(8.dp)
//            ) {
//                LazyColumn(modifier = Modifier.fillMaxSize()) {
//                    item {
//                        Text(
//                            text = brevIQViewModel.extractedText.value,
//                            style = MaterialTheme.typography.bodyLarge,
//                            modifier = Modifier.padding(8.dp),
//                            textAlign = TextAlign.Center
//                        )
//                    }
//                }
//            }
//            Card(
//                modifier = Modifier
//                    .padding(start = 3.dp, end = 3.dp, bottom = 40.dp)
//                    .fillMaxWidth()
//                    .height(60.dp)
//                    .clickable {
//                        launcher.launch("image/*")
//                    },
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFFCCE5E3)
//                )
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                  //  horizontalArrangement = Arrangement.SpaceBetween,
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    Box(modifier = Modifier.weight(3.5f).fillMaxHeight(),
//                        contentAlignment = Alignment.Center
//                        ) {
//                        Text(
//                            text = "Upload an Image",
//                            textAlign = TextAlign.Center
//                        )
//                    }
//                    IconButton(
//                        modifier = Modifier.weight(0.5f),
//                        onClick = {
//                            bitmap?.let { brevIQViewModel.processImage(it, apiKey) }//used to target or call the IMAGETOTEXT response
//                        },
//                        enabled = bitmap != null
//                    ) {
//                        Icon(imageVector = Icons.Default.Send, contentDescription = " ")
//                    }
//                }
//            }
//        }
//
//    }
//    Spacer(modifier = Modifier.height(16.dp))
}
