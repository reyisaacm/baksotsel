package org.reynhart.baksotsel

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
    currentUser: LoginUserModel,
    locList: Flow<List<LoginUserModel>>
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

    val markerList = remember{mutableStateListOf<LoginUserModel>()}
    LaunchedEffect(Unit){
            locList.collect{
                for(user in it){
                    val userInList = markerList.firstOrNull{x-> x.id == user.id}
                    if(userInList == null && user.isActive){ //if user does not exist in list and active
                        markerList.add(user)
                    } else if(userInList != null &&  user.isActive == false){ //if user exist in list and not active
                        markerList.removeIf { x->x.id == user.id }
                    } else  if(userInList != null && user.isActive){ //if user exist and not active
                        val latCompare = (userInList.currentCoordinateLat == user.currentCoordinateLat)
                        val longCompare = (userInList.currentCoordinateLong == user.currentCoordinateLong)
                        if(latCompare && longCompare){ // location is still the same

                        } else { //location changed
                            markerList.removeIf { x->x.id == user.id }
                            markerList.add(user)
                        }
                    }

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
                position = LatLng(it.currentCoordinateLat, it.currentCoordinateLong)
            )

            if(it.type == "c"){
                MarkerComposable(
                    state = markerState
                ){
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
            }

            if(it.type == "tb"){
                MarkerComposable(
                    state = markerState
                ){
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