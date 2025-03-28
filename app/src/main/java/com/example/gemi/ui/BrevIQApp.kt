package com.example.gemi.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

//used to manage the screen
enum class BrevIQAppScreen(){
    Login,
    SignUp,
    SignIn,
    HomeScreen,
    SummarizationScreen,
    PrescriptionScreen,
    History,
    Profile,
    SavedScreen
}
val auth= FirebaseAuth.getInstance()

@Composable
fun BrevIQApp(brevIQViewModel: BrevIQViewModel= viewModel(),
              navHostController: NavHostController= rememberNavController()){

    val isvisible by brevIQViewModel.isvisible.collectAsState()
    val user by brevIQViewModel.user.collectAsState()

    val logoutClicked by brevIQViewModel.logoutClicked.collectAsState()//used to manage the the manage the logout or alert screen


    auth.currentUser?.let { brevIQViewModel.setUser(it) }
    val backStackEntry by navHostController.currentBackStackEntryAsState()//used to control the back buttton navigation
    val currentScreen =BrevIQAppScreen.valueOf(
        backStackEntry?.destination?.route?:BrevIQAppScreen.HomeScreen.name
    )

    // start destination will changes according to the user
    val startDestination = if (user == null) {
        BrevIQAppScreen.Login.name
    } else {
        BrevIQAppScreen.HomeScreen.name
    }

   
    if (isvisible){
        SplashScreen()
    }

    else{
        NavHost(navController = navHostController, startDestination =startDestination) {
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
                SummariztionScreen(brevIQViewModel = brevIQViewModel,navHostController)
            }
            composable(route = BrevIQAppScreen.HomeScreen.name){
                HomeScreen(brevIQViewModel = brevIQViewModel, navHostController =navHostController )
            }
            composable(route = BrevIQAppScreen.PrescriptionScreen.name){
                ImageToTextScreen(brevIQViewModel = brevIQViewModel)
            }
            composable(route = BrevIQAppScreen.History.name){
                HistoryScreen(brevIQViewModel,navController = navHostController)
            }
            composable(route = BrevIQAppScreen.Profile.name){
                ProfileScreen(brevIQViewModel = brevIQViewModel)
            }
            composable(route = BrevIQAppScreen.SavedScreen.name){
                SaveScreen(brevIQViewModel = brevIQViewModel, navController =navHostController )
            }
        }
        if (logoutClicked){
            AlertCheck(onYesButtonPressed = {
                auth.signOut()
                brevIQViewModel.clearData()
                brevIQViewModel.setLogoutStatus(false)
                navHostController.navigate(BrevIQAppScreen.Login.name) {
                    popUpTo(BrevIQAppScreen.Login.name) {
                        inclusive = true
                    }
                }
            },
                onNoButtonPressed = {
                    brevIQViewModel.setLogoutStatus(false)
                }
            )
        }
    }
    
}
@Composable
fun AlertCheck(
    onYesButtonPressed:()->Unit,
    onNoButtonPressed:()->Unit

){
    AlertDialog(
        title = {
            Text(text = "Logout?", fontWeight = FontWeight.Bold)
        },
        containerColor = Color.White,
        text = {
            Text(text = "Are you sure you want to Logout")
        },
        confirmButton = {
            TextButton(onClick = {
                onYesButtonPressed()
            }) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onNoButtonPressed()
            }) {
                Text(text = "No")
            }
        },
        onDismissRequest = {
            onNoButtonPressed()
        }
    )
}
