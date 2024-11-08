package org.reynhart.baksotsel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
actual fun GoogleMapView() {

    val latLng: LatLng = LatLng(-6.2274808, 106.8160314)

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
            snippet = "Test Snippet"
        )

    }

}