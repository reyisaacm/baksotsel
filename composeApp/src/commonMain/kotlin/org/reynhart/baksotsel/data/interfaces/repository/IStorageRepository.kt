package org.reynhart.baksotsel.data.interfaces.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant
import org.reynhart.baksotsel.models.LoginUserModel

interface IStorageRepository {
    suspend fun storeUserData(data: LoginUserModel)
    suspend fun getUserData():LoginUserModel?
    suspend fun clearUserData(data: LoginUserModel)
    suspend fun getUserDataStream():Flow<List<LoginUserModel>>
    suspend fun sendUserLocation(id: String, latitude: Double, longitude: Double)
    suspend fun sendLastUpdate(id: String, timestamp: Instant)
}