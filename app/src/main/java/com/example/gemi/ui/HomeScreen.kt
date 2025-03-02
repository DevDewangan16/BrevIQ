package com.example.gemi.ui

import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gemi.R

@Composable
fun HomeScreen(brevIQViewModel: BrevIQViewModel,
               navHostController: NavHostController){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(vertical = 30.dp),

        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement =Arrangement.spacedBy(5.dp),
        ) {
        item { 
            Text(
                text = "Welcome to BrevIQ",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                fontSize = 30.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
                )
        }
        item {
            Text(
                text = "Transform Complexity into Clarity – AI-Powered Summaries & Insights!",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        navHostController.navigate(BrevIQAppScreen.SummarizationScreen.name)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEEDEF6)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.a),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
//                            text = "\uD83D\uDCC4 Summarize Documents & Notes",
                            text="\uD83D\uDCA1 Instant Knowledge Extraction",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            //text = "Upload PDFs, text, or handwritten notes to generate concise summaries.",
                            text = "Get instant answers and key takeaways from text-based questions and media.",
                            fontSize = 15.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        navHostController.navigate(BrevIQAppScreen.PrescriptionScreen.name)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFCCE5E3)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.b),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text = "\uD83D\uDCD1 Analyze Medical Prescriptions",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Scan and convert complex prescriptions into easy-to-understand text.",
                            fontSize = 15.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        navHostController.navigate(BrevIQAppScreen.History.name)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFEE1B6)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.c),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text ="\uD83D\uDCC2 View Recent Summaries",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Access and manage your latest processed documents.",
                            fontSize = 15.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        navHostController.navigate(BrevIQAppScreen.SavedScreen.name)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFDCEDF)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.d),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text ="\uD83D\uDCD6 Keep Your Key Insights at Your Fingertips!",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Access, manage, and revisit your AI-generated summaries anytime, anywhere.",
                            fontSize = 15.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 300.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        navHostController.navigate(BrevIQAppScreen.Profile.name)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFBAE75)
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.e),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text ="\uD83D\uDC64 Profile",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Personalize your experience & manage your preferences.",
                            fontSize = 15.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
    }
}
