package org.reynhart.baksotsel

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerInfoWindowComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.reynhart.baksotsel.models.LocationModel
import org.reynhart.baksotsel.models.LoginUserModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Composable
actual fun GoogleMapView(
    currentUser: LoginUserModel,
    locList: SnapshotStateList<LoginUserModel>
) {
    val latLng: LatLng = LatLng(currentUser.currentCoordinateLat, currentUser.currentCoordinateLong)
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

//    LaunchedEffect(markerStateList.count()){
//        println(markerStateList)
//    }




    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings
    ) {
        locList.forEach{user->
            val markerState = MarkerState(
                position = LatLng(user.currentCoordinateLat, user.currentCoordinateLong)
            )

            if(user.type == "c"){
                MarkerInfoWindowComposable(
                    state = markerState,
                     infoContent = {
                        Column(modifier = Modifier.background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        ).padding(8.dp)) {
                            Text(user.name)
                        }
                    },
                    content = {
                        Column(modifier = Modifier.width(36.dp).height(36.dp).background(
                            color = Color.Red,
                            shape = CircleShape
                        ), verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Favorite Icon",
                                tint = Color.White,
                                modifier = Modifier.width(24.dp).height(24.dp)
                            )
                        }
                    }

                )
            }

            if(user.type == "tb"){
                MarkerInfoWindowComposable(
                    state = markerState,
                    infoContent = {
                        Column(modifier = Modifier.background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        ).padding(8.dp)) {
                            Text(user.name)
                        }
                    },
                    content = {
                        Column(modifier = Modifier.width(36.dp).height(36.dp).background(
                            color = Color.Green,
                            shape = CircleShape
                        ), verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Filled.ShoppingCart,
                                contentDescription = "Favorite Icon",
                                tint = Color.White,
                                modifier = Modifier.width(24.dp).height(24.dp)
                            )
                        }
                    }

                )
            }

//            Marker(
//                state = markerState,
//                title = "Telkomsel Smart Office",
//                snippet = "Test Snippet",
//            )
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