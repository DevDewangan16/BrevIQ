package com.example.gemi.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gemi.R

@Composable
fun LoginUi(brevIQViewModel: BrevIQViewModel,navHostController: NavHostController){
    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            ),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.login), contentDescription = "",
            modifier = androidx.compose.ui.Modifier.fillMaxWidth()
            )
        Text(text = "Welcome to BrevIQ !",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
            fontSize = 30.sp,
            color = Color.Black
            )
        Text(text = "Turn complexity into clarity—AI-driven insights from documents and visuals. See beyond words with smart summaries from files and graphics!\" \uD83D\uDE80",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
            fontSize = 15.sp,
            color=Color.Black)
        Button(
            onClick = {
                navHostController.navigate(BrevIQAppScreen.SignIn.name)
            },
            colors = ButtonDefaults.buttonColors(
                Color.Black
            ),
            shape = ButtonDefaults.elevatedShape,
            modifier = androidx.compose.ui.Modifier.padding(15.dp)

        ) {
            Text(text = "Continue to Login",
                fontSize = 15.sp,
                modifier = androidx.compose.ui.Modifier.padding(5.dp))
        }
        Text(text = "By continuing, you agree to our Terms of Service and Privacy Policy",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            fontSize = 10.sp,
            color=Color.Black)

    }
}