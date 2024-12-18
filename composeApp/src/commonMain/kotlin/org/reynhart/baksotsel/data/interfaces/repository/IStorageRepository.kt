package org.reynhart.baksotsel.data.interfaces.repository

import kotlinx.coroutines.flow.Flow
import org.reynhart.baksotsel.models.LoginUserModel

interface IStorageRepository {
    suspend fun storeUserData(data: LoginUserModel)
    suspend fun getUserData():LoginUserModel?
    suspend fun clearUserData(data: LoginUserModel)
    suspend fun getUserDataStream():Flow<List<LoginUserModel>>
}