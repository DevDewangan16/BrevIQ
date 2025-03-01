package com.example.gemi.ui



import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gemi.ui.data.DataBase

@Composable
fun SaveScreen(brevIQViewModel: BrevIQViewModel,navController: NavController) {
    val databaseList by brevIQViewModel.databaseList.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Saved Information")

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(databaseList) { entry ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    //elevation =4.dp,
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFEE1B6)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Request: ${entry.request}")
                        Text(text = "Response: ${entry.response}")
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Button(onClick = {
                                brevIQViewModel.removeFromCart(entry)
                            },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF2F2F2F)
                                )) {
                                Text(text = "Remove")
                            }
                        }
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
