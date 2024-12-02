package org.reynhart.baksotsel

import android.annotation.SuppressLint
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.reynhart.baksotsel.models.LocationModel


@SuppressLint("MissingPermission")
actual suspend fun getCurrentLocation(): Flow<LocationModel> {
    return callbackFlow {
        val client = LocationServices.getFusedLocationProviderClient(applicationContext)
        client.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY,null).addOnSuccessListener {
            if(it != null){
                this@callbackFlow.trySend(LocationModel(id="",latitude=it.latitude,longitude=it.longitude))
                close()
            } else {
                this@callbackFlow.trySend(LocationModel(id="",latitude=0.0,longitude=0.0))
                close()
            }
        }

        awaitClose {}
    }
}

//@SuppressLint("MissingPermission")
//suspend fun FusedLocationProviderClient.locationFlow(): Flow<LocationModel> = callbackFlow {
//    val locationRequest = LocationRequest.create().apply {
//        interval = TimeUnit.SECONDS.toMillis(10)
//        fastestInterval = TimeUnit.SECONDS.toMillis(10)
//        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//    }
//    val callBack = object : LocationCallback() {
//        override fun onLocationResult(locationResult: LocationResult) {
//            super.onLocationResult(locationResult)
//            val location = locationResult.lastLocation
//            val userLocation  = if(location != null) LocationModel(
//                latitude = location.latitude,
//                longitude = location.longitude
//            ) else LocationModel(
//                latitude = 0.0,
//                longitude = 0.0
//            )
//            try {
//                this@callbackFlow.trySend(userLocation).isSuccess
//                removeLocationUpdates(this)
//            } catch (e: Exception) {
//            }
//        }
//    }
//
//    requestLocationUpdates(
//        locationRequest,
//        callBack,
//        Looper.getMainLooper()
//    ).addOnFailureListener { e ->
//        close(e)
//    }
//    awaitClose {
//        removeLocationUpdates(callBack)
//    }
//}