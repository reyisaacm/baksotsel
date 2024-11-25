package org.reynhart.baksotsel

import kotlinx.coroutines.flow.Flow
import org.reynhart.baksotsel.models.LocationModel

actual suspend fun getCurrentLocation(): Flow<LocationModel> {
    TODO("Not yet implemented")
}