package org.reynhart.baksotsel

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.reynhart.baksotsel.models.LocationModel
import platform.posix.close

@OptIn(ExperimentalForeignApi::class)
actual suspend fun getCurrentLocation(): Flow<LocationModel> {
    val locationManager: IosLocationManager = IosLocationManager()

    val locationResult = locationManager.requestCurrentLocation()

   if (locationResult.isSuccess) {
        locationResult.getOrNull()?.let {
            val model = LocationModel(
                id="",
                latitude = it.coordinate().useContents { latitude },
                longitude = it.coordinate().useContents { longitude },
            )
            return flow {
                emit(model)
            }
        }
       return flow{
           emit(LocationModel(id="", latitude = 0.0, longitude = 0.0))
       }
    } else {
        return flow{
            emit(LocationModel(id="", latitude = 0.0, longitude = 0.0))
        }
   }
}

actual suspend fun getLocationUpdates(): Flow<LocationModel> {
    return flow{
        emit(LocationModel(id="", latitude = 0.0, longitude = 0.0))
    }
}

actual fun checkIfGPSIsEnabled(): Boolean {
    TODO("Not yet implemented")
}