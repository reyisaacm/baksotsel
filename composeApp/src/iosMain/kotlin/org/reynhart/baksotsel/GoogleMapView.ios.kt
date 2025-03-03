package org.reynhart.baksotsel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import cocoapods.GoogleMaps.GMSAdvancedMarker.Companion.markerImageWithColor
import cocoapods.GoogleMaps.GMSCameraPosition
import cocoapods.GoogleMaps.GMSMapView
import cocoapods.GoogleMaps.GMSMapView.Companion.mapWithFrame
import cocoapods.GoogleMaps.GMSMarker
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.Flow
import org.reynhart.baksotsel.models.LocationModel
import org.reynhart.baksotsel.models.LoginUserModel
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.Foundation.NSMutableArray
import platform.UIKit.UIColor
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun GoogleMapView(
    currentUser: LoginUserModel,
    locList: SnapshotStateList<LoginUserModel>
){

//    val nsMutableArray: NSMutableArray = locList as NSMutableArray
//
//    val markerList = remember{ mutableStateListOf<LocationModel>() }

//    LaunchedEffect(true){
//        locList.collect{
//            val isAlreadyExist = markerList.firstOrNull{x-> x.id == it.id} != null
//            if(!isAlreadyExist){
//                markerList.add(
//                    LocationModel(
//                    id= it.id,
//                    latitude = it.currentCoordinateLat,
//                    longitude = it.currentCoordinateLong
//                )
//                )
//            }
//        }
//
//    }
//    var listOfMarker = remember { mutableStateListOf<LoginUserModel>() }
//    LaunchedEffect(locList){
////        listOfMarker.clear()
//        locList.forEach {
//            listOfMarker.add(it)
//        }
//    }

    val camera: GMSCameraPosition=
        GMSCameraPosition.cameraWithLatitude(
            latitude = currentUser.currentCoordinateLat,
            longitude = currentUser.currentCoordinateLong,
            zoom = 15f
        )
    var locListCount = 0

    val mapView =  GMSMapView()
    //camera?.let { mapWithFrame(frame = mapView.frame, camera = it) }
    mapView.camera=camera
    mapView.settings.zoomGestures = true
    mapView.settings.consumesGesturesInView = true
    var factory = remember(GMSMapView) {
        {mapView}
    }
    locList.forEach {
        val marker = GMSMarker()
        marker.position = CLLocationCoordinate2DMake(
            latitude = it.currentCoordinateLat,
            longitude = it.currentCoordinateLong
        )
        marker.title = ""
        marker.snippet = ""
        marker.map = mapView
//        GMSMarker().apply {
//            this.position = CLLocationCoordinate2DMake(
//                latitude = it.currentCoordinateLat,
//                longitude = it.currentCoordinateLong
//            )
//            this.title = "Telkomsel Smart Office"
//            this.snippet = "Test Snippet"
////            markerImageWithColor(UIColor.redColor)
////                    this.markerImageWithColor(color = UIColor.redColor)
//        }.map = mapView
//
//        GMSMarker().apply {
//            this.position = CLLocationCoordinate2DMake(
//                latitude = it.currentCoordinateLat+0.0005,
//                longitude = it.currentCoordinateLong
//            )
//            this.title = "Telkomsel Smart Office"
//            this.snippet = "Test Snippet"
////            markerImageWithColor(UIColor.greenColor)
////                    this.markerImageWithColor(color = UIColor.redColor)
//        }.map = mapView

        factory = remember(GMSMapView) {
            {mapView}
        }

        UIKitView(
            factory = factory,
            modifier = Modifier.fillMaxSize(),
            onRelease = {
                it.removeFromSuperview()
            },
        )

    }






//    Column {
//        Text("$locListCount")
//    }
}