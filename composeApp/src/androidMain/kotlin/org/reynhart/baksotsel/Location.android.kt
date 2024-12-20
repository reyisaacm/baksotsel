package org.reynhart.baksotsel

import android.annotation.SuppressLint
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.reynhart.baksotsel.models.LocationModel
import java.util.concurrent.TimeUnit


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

@SuppressLint("MissingPermission")
actual suspend fun getLocationUpdates(): Flow<LocationModel> {
    return callbackFlow {
        val client = LocationServices.getFusedLocationProviderClient(applicationContext)
        val locationRequest =
            LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, TimeUnit.SECONDS.toMillis(30)).build()
        val callBack = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                val location = locationResult.lastLocation
                val userLocation = if (location != null) LocationModel(
                    latitude = location.latitude,
                    longitude = location.longitude,
                    id = "0"
                ) else LocationModel(
                    latitude = 0.0,
                    longitude = 0.0,
                    id = "0"
                )
                try {
                    this@callbackFlow.trySend(userLocation).isSuccess
//                    client.removeLocationUpdates(this)
                } catch (e: Exception) {
                }
            }
        }

    client.requestLocationUpdates(
        locationRequest,
        callBack,
        Looper.getMainLooper()
    ).addOnFailureListener { e ->
        close(e)
    }
        awaitClose {
            client.removeLocationUpdates(callBack)
        }
    }
}