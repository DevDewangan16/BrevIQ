package com.example.gemi.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun HistoryScreen(brevIQViewModel: BrevIQViewModel,navController: NavController) {
    val historyList by brevIQViewModel.historyList.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Request History")

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(historyList) { entry ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    //elevation =4.dp,
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFEE1B6)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Request: ${entry.request}")
                        Text(text = "Response: ${entry.response}")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2F2F2F)
            )) {
            Text("Back to Home")
        }
    }
}
