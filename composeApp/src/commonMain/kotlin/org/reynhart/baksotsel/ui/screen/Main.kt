package org.reynhart.baksotsel.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.reynhart.baksotsel.GoogleMapView
import org.reynhart.baksotsel.data.interfaces.repository.IStorageRepository
import org.reynhart.baksotsel.models.DialogModel
import org.reynhart.baksotsel.models.LoginUserModel
import org.reynhart.baksotsel.ui.theme.onPrimaryLight
import org.reynhart.baksotsel.ui.theme.primaryLight
import org.reynhart.baksotsel.ui.widgets.BaksoDialog
import org.reynhart.baksotsel.viewmodels.LoginViewModel
import org.reynhart.baksotsel.viewmodels.MainViewModel
import org.reynhart.baksotsel.viewmodels.states.MainStates

@Composable
fun Main(navController: NavController,   vm: MainViewModel = koinViewModel()){
    var isShowLogoutDialog by remember { mutableStateOf(false) }
    val eventState by vm.eventState


    if(eventState == MainStates.Clear){
        isShowLogoutDialog = false
        navController.navigate("Login")
    } else if(eventState == MainStates.MapLoaded){
        val loginData = vm.loginData
        GoogleMapView(
            currentLoc = loginData
        )
    }

    ExtendedFloatingActionButton(
        onClick = {
           isShowLogoutDialog = true
        },
        icon = { Icon(Icons.Filled.Close, "Logout") },
        text = { Text(text = "Logout") },
        containerColor = primaryLight,
        contentColor = onPrimaryLight
    )

    if(isShowLogoutDialog){
        BaksoDialog(
            "Dengan menutup halaman ini anda akan kembali ke halaman login",
            itemMap = vm.dialogOptions,
            onDismissDialogBox = {},
            onSelectedItem ={ it ->
                if(it == "ok"){
                    vm.onLogoutClick()
                } else {
                    isShowLogoutDialog = false
                }
            }
        )
    }


}