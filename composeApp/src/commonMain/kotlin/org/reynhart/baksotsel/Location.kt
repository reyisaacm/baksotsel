package org.reynhart.baksotsel

import kotlinx.coroutines.flow.Flow
import org.reynhart.baksotsel.models.LocationModel

expect suspend fun getCurrentLocation(): Flow<LocationModel>
expect suspend fun getLocationUpdates(): Flow<LocationModel>