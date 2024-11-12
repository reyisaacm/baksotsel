package org.reynhart.baksotsel
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.KoinApplication
import org.reynhart.baksotsel.di.sharedModule
import org.reynhart.baksotsel.ui.screen.Login
import org.reynhart.baksotsel.ui.screen.Main


@Composable
fun App() {
    KoinApplication(application = {
        sharedModule
    }){
        NavigationRoutes()

    }

}

@Composable
fun NavigationRoutes(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "Login"){
        composable(route = "Login") {
            Login(navController)
        }
        composable(route="Main") {
            Main(navController)
        }
    }
}