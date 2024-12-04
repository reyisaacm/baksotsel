package org.reynhart.baksotsel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
import org.reynhart.baksotsel.ui.screen.Splash


@Composable
fun App() {
    KoinContext(context = koinApplication {
        modules(sharedModule)
    }.koin) {
        Splash()
    }
}

