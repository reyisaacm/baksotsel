package org.reynhart.baksotsel

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.reynhart.baksotsel.models.LocationModel
import org.reynhart.baksotsel.models.LoginUserModel

@Composable
actual fun GoogleMapView(
    currentLoc: Flow<LoginUserModel>
) {
    val latLng: LatLng = LatLng(-6.2306647, 106.8148273)
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

    val markerList = remember{mutableStateListOf<LocationModel>()}

    LaunchedEffect(Unit){
            currentLoc.collect{
                val isAlreadyExist = markerList.firstOrNull{x-> x.id == it.id} != null
                if(!isAlreadyExist){
                    markerList.add(LocationModel(
                        id= it.id,
                        latitude = it.currentCoordinateLat,
                        longitude = it.currentCoordinateLong
                    ))
                }
            }

    }

//    LaunchedEffect(markerStateList.count()){
//        println(markerStateList)
//    }




    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings
    ) {
        markerList.forEach{
            val markerState = MarkerState(
                position = LatLng(it.latitude, it.longitude)
            )

            Marker(
                state = markerState,
                title = "Telkomsel Smart Office",
                snippet = "Test Snippet",
            )
        }
    }



//    ExtendedFloatingActionButton(onClick = {
//        markerList.removeAll{x->x.id != ""}
//        markerList.add(LocationModel(
//            id= "test123",
//            latitude = -6.2273296,
//            longitude = 106.8296376
//        ))
//    }){
//        Text("Test")
//    }

}