package com.example.gemi.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gemi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(brevIQViewModel: BrevIQViewModel,navHostController: NavHostController){
    val name by brevIQViewModel.name.collectAsState()
    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.signin), contentDescription = "Logo",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )
        Text(
            text = "Welcome Back to BrevIQ!",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
            fontSize = 30.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Unlock AI-powered insights from your content effortlessly",
            modifier = Modifier
                .fillMaxWidth().padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
            fontSize = 15.sp,
            color = Color.Black
        )

        OutlinedTextField(
            value = name,
            onValueChange = {
                brevIQViewModel.setName(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ) ,
            label = {
                Text(text = "Email Address")
            },
            placeholder = {
                Text(text = "Enter your Email")
            },
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedLabelColor = Color.Black
            ),

            )
        OutlinedTextField(
            value = name,
            onValueChange = {
                brevIQViewModel.setName(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ) ,
            label = {
                Text(text = "Password")
            },
            placeholder = {
                Text(text = "Enter your Password")
            },
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
            singleLine = true,
            colors =OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedLabelColor = Color.Black
            ),

            )
        Button(
            onClick = {
                navHostController.navigate(BrevIQAppScreen.HomeScreen.name)
            },
            colors = ButtonDefaults.buttonColors(
                Color.Black
            ),
            shape = ButtonDefaults.elevatedShape,

            ) {
            Text(text = "Sign In",
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 5.dp))
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don't have an account?",
                fontSize = 10.sp,
                color= Color.Black,
            )
            Text(
                text = "Sign Up",
                fontSize = 10.sp,
                color= Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navHostController.navigate(BrevIQAppScreen.SignUp.name)
                }
            )
        }

    }
}