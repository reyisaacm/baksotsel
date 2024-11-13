package org.reynhart.baksotsel
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.KoinApplication
import org.koin.compose.KoinContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.reynhart.baksotsel.data.dataProvider.KStoreStorage
import org.reynhart.baksotsel.data.interfaces.dataProvider.ILocalStorage
import org.reynhart.baksotsel.data.interfaces.repository.IStorageRepository
import org.reynhart.baksotsel.data.repository.StorageRepository
import org.reynhart.baksotsel.di.sharedModule
import org.reynhart.baksotsel.ui.screen.Login
import org.reynhart.baksotsel.ui.screen.Main


@Composable
fun App() {
    KoinContext(context = koinApplication {
        modules(sharedModule)
    }.koin) {
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