package org.reynhart.baksotsel.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.reynhart.baksotsel.GoogleMapView
import org.reynhart.baksotsel.ui.theme.onPrimaryLight
import org.reynhart.baksotsel.ui.theme.primaryLight

@Composable
fun Main(navController: NavController){
    GoogleMapView()

    ExtendedFloatingActionButton(
        onClick = {
            navController.navigate("Login")
        },
        icon = { Icon(Icons.Filled.Close, "Logout") },
        text = { Text(text = "Logout") },
        containerColor = primaryLight,
        contentColor = onPrimaryLight
    )
}