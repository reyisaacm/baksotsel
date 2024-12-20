package org.reynhart.baksotsel.data.interfaces.dataProvider

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant
import org.reynhart.baksotsel.models.LoginUserModel

interface IDbStorage {
    suspend fun storeData(data: LoginUserModel)
    suspend fun deleteData(data: LoginUserModel)
    suspend fun getDataStream(): Flow<List<LoginUserModel>>
    suspend fun updateLocationData(id: String, latitude: Double, longitude: Double)
    suspend fun updateLastUpdate(id: String, timestamp: Instant)
}