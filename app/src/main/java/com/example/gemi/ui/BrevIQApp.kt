package com.example.gemi.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class BrevIQAppScreen(){
    Login,
    SignUp,
    SignIn,
    HomeScreen,
    SummarizationScreen,
    PrescriptionScreen
}

@Composable
fun BrevIQApp(brevIQViewModel: BrevIQViewModel= viewModel(),
              navHostController: NavHostController= rememberNavController()){

    val apiKey = "AIzaSyDbGhLvg47UU1tY7O0LS7dbeho1dFEuvPk"

    val isvisible by brevIQViewModel.isvisible.collectAsState()

    if (isvisible){
        SplashScreen()
    }else{
        NavHost(navController = navHostController, startDestination =BrevIQAppScreen.Login.name ) {
            composable(route = BrevIQAppScreen.Login.name){
                LoginUi(brevIQViewModel = brevIQViewModel,navHostController=navHostController)
            }
            composable(route = BrevIQAppScreen.SignIn.name){
                SignInScreen(brevIQViewModel = brevIQViewModel,navHostController)
            }
            composable(route = BrevIQAppScreen.SignUp.name){
                SignUpScreen(brevIQViewModel = brevIQViewModel,navHostController)
            }
            composable(route = BrevIQAppScreen.SummarizationScreen.name){
                SummariztionScreen(brevIQViewModel = brevIQViewModel)
            }
            composable(route = BrevIQAppScreen.HomeScreen.name){
                HomeScreen(brevIQViewModel = brevIQViewModel, navHostController =navHostController )
            }
            composable(route = BrevIQAppScreen.PrescriptionScreen.name){
                ImageToTextScreen(brevIQViewModel = brevIQViewModel, apiKey =apiKey )
            }
        }
    }
    
}