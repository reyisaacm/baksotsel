package org.reynhart.baksotsel.ui.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.jordond.connectivity.Connectivity
import dev.jordond.connectivity.compose.rememberConnectivityState

@Composable
fun BaksoDeviceCheck(){
    val state = rememberConnectivityState {
        // Optional configurator for ConnectivityOptions
        autoStart = true
    }

    var isShowError by remember { mutableStateOf(false) }

    when (state.status) {
        is Connectivity.Status.Connected -> {
            isShowError = false
        }
        is Connectivity.Status.Disconnected ->{
            isShowError = true
            if(isShowError){
                BaksoErrorBox(
                    message = "This app required internet connectivity, please connect your device to the internet.",
                    onDismissRequest = {}
                )
            }
        }
        else -> {}
    }
}