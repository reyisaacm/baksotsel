package org.reynhart.baksotsel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import kotlinx.coroutines.flow.Flow
import org.reynhart.baksotsel.models.LoginUserModel

@Composable
actual fun GoogleMapView(
    currentLoc: Flow<LoginUserModel>
) {

    val latLng: LatLng = LatLng(0.0, 0.0)

    val cameraPositionState = rememberCameraPositionState {
        latLng.let { position = CameraPosition.fromLatLngZoom(it, 15f) }
    }

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomGesturesEnabled = true
            )
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings
    ) {

        Marker(
            state = MarkerState(
                position = LatLng(-6.2306647, 106.8148273)
            ),
            title = "Telkomsel Smart Office",
            snippet = "Test Snippet",
        )

    }

}