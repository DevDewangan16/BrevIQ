package com.example.gemi.ui

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gemi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(brevIQViewModel: BrevIQViewModel,
                 navHostController: NavHostController){
    val baseContext = LocalContext.current
    val name by brevIQViewModel.name.collectAsState()
    val email by brevIQViewModel.email.collectAsState()
    val password by brevIQViewModel.password.collectAsState()
    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.signupscreen), contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.FillBounds
            )
        Text(
            text = "Join BrevIQ Today!",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
            fontSize = 30.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Experience AI-driven clarity for your content.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
            fontSize = 15.sp,
            color = Color.Black
        )
        OutlinedTextField(
            value = name,
            onValueChange = {
            brevIQViewModel.setName(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ) ,
            label = {
                Text(text = "Full Name")
            },
            placeholder = {
                Text(text = "Enter your name")
            },
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
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
            value = email,
            onValueChange = {
                brevIQViewModel.setEmail(it)
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
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
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

        OutlinedTextField(
            value = password,
            onValueChange = {
                brevIQViewModel.setPassword(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ) ,
            label = {
                Text(text = "Password")
            },
            placeholder = {
                Text(text = "Create a Strong Password")
            },
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
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
        OutlinedTextField(
            value = name,
            onValueChange = {
                brevIQViewModel.setName(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ) ,
            label = {
                Text(text = "Confirm Password")
            },
            placeholder = {
                Text(text = "Re-enter Password")
            },
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
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
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            navHostController.navigate(BrevIQAppScreen.HomeScreen.name)

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            },
            colors = ButtonDefaults.buttonColors(
                Color.Black
            ),
            shape = ButtonDefaults.elevatedShape,

        ) {
            Text(text = "Sign Up",
                fontSize = 15.sp,
                modifier = androidx.compose.ui.Modifier.padding(5.dp))
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Already have an account?",
                fontSize = 10.sp,
                color=Color.Black,
                )
            Text(
                text = "Sign In",
                fontSize = 10.sp,
                color=Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navHostController.navigate(BrevIQAppScreen.SignIn.name)
                }
            )
        }

    }
}