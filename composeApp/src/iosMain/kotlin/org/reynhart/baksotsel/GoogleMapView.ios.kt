package org.reynhart.baksotsel

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.viewinterop.UIKitView
import cocoapods.GoogleMaps.GMSAdvancedMarker.Companion.markerImageWithColor
import cocoapods.GoogleMaps.GMSCameraPosition
import cocoapods.GoogleMaps.GMSMapView
import cocoapods.GoogleMaps.GMSMapView.Companion.mapWithFrame
import cocoapods.GoogleMaps.GMSMarker
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.Flow
import org.reynhart.baksotsel.models.LoginUserModel
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.UIKit.UIColor

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun GoogleMapView(
    currentLoc: Flow<LoginUserModel>
){

    UIKitView(
        factory = {
            val camera: GMSCameraPosition? =
                GMSCameraPosition.cameraWithLatitude(
                    latitude = -6.2274808,
                    longitude = 106.8160314,
                    zoom = 15f
                )


            val mapView =  GMSMapView()
            camera?.let { mapWithFrame(frame = mapView.frame, camera = it) }
            mapView.settings.zoomGestures = true
            mapView.settings.consumesGesturesInView = true

                GMSMarker().apply {
                    this.position = CLLocationCoordinate2DMake(
                        latitude = -6.2274808,
                        longitude = 106.8160314
                    )
                    this.title = "Telkomsel Smart Office"
                    this.snippet = "Test Snippet"
                    markerImageWithColor(UIColor.redColor)
//                    this.markerImageWithColor(color = UIColor.redColor)
                }.map = mapView

            mapView
        },
//        modifier = modifier,
        onRelease = {
            it.removeFromSuperview()
        }
    )
}