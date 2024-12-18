package org.reynhart.baksotsel.data.interfaces.dataProvider

import kotlinx.coroutines.flow.Flow
import org.reynhart.baksotsel.models.LoginUserModel

interface IDbStorage {
    suspend fun storeData(data: LoginUserModel)
    suspend fun deleteData(data: LoginUserModel)
    suspend fun getDataStream(): Flow<List<LoginUserModel>>
}