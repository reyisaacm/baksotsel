package org.reynhart.baksotsel.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel
import org.reynhart.baksotsel.viewmodels.LoginViewModel
import org.reynhart.baksotsel.viewmodels.SplashViewModel
import org.reynhart.baksotsel.viewmodels.states.SplashStates

@Composable
fun Splash(vm: SplashViewModel = koinViewModel()){
    val eventState by vm.eventState

    if(eventState == SplashStates.Loading){
        Column (modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
        }
    } else {
        val startDestination = if(eventState == SplashStates.LoggedIn)"Main" else "Login"
        val navController = rememberNavController()

        NavHost(navController, startDestination = startDestination){
            composable(route = "Login") {
                Login(navController)
            }
            composable(route="Main") {
                Main(navController)
            }
        }
    }

}