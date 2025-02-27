package com.example.gemi.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun SummariztionScreen(brevIQViewModel: BrevIQViewModel) {
    var userInput by remember { mutableStateOf(TextFieldValue("")) }
    val response by brevIQViewModel.response.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            //.padding(16.dp)
    ) {
        //Text(text = "Ask Gemini AI", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))
        Column( modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween) {
            Box (modifier = Modifier
                .height(600.dp)
                .weight(1f) // ✅ Ensures TextField stays at the bottom even when empty
                .fillMaxWidth()
                .padding(8.dp)) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item{
                        Text(
                            text = "Response: $response",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
            OutlinedTextField(
                value = userInput, onValueChange = { userInput = it },
                modifier = Modifier.fillMaxWidth()
                    // .weight(1f)
                    .padding(start = 3.dp, end = 3.dp, bottom = 17.dp)
                ,placeholder = { Text("Ask Gemini...")},
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
                trailingIcon = {
                    IconButton(onClick = {
                        brevIQViewModel.fetchResponse(userInput.text)
                    }) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = " ")
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}