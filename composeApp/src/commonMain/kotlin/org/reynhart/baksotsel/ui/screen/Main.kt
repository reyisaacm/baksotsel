package org.reynhart.baksotsel.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import org.reynhart.baksotsel.GoogleMapView
import org.reynhart.baksotsel.models.DialogModel
import org.reynhart.baksotsel.ui.theme.onPrimaryLight
import org.reynhart.baksotsel.ui.theme.primaryLight
import org.reynhart.baksotsel.ui.widgets.BaksoDialog

@Composable
fun Main(navController: NavController){
    var isShowLogoutDialog by remember { mutableStateOf(false) }
    val dialogOptions = listOf<DialogModel>(
        DialogModel(label="OK", value = "ok", isPrimaryColor = true),
        DialogModel(label="Batal", value = "cancel", isPrimaryColor = false),
        )

    GoogleMapView()

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
            itemMap = dialogOptions,
            onSelectedItem ={ it ->
                if(it == "ok"){
                    isShowLogoutDialog = false
                    navController.navigate("Login")
                } else {
                    isShowLogoutDialog = false
                }
            }
        )
    }


}