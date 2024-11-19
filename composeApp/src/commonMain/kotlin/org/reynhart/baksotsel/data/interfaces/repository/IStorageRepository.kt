package org.reynhart.baksotsel.data.interfaces.repository

import org.reynhart.baksotsel.models.LoginUserModel

interface IStorageRepository {
    suspend fun storeUserData(data: LoginUserModel)
    suspend fun getUserData():LoginUserModel?
    suspend fun clearUserData()
}