package org.reynhart.baksotsel.data.repository

import kotlinx.coroutines.flow.Flow
import org.reynhart.baksotsel.data.interfaces.dataProvider.IDbStorage
import org.reynhart.baksotsel.data.interfaces.dataProvider.ILocalStorage
import org.reynhart.baksotsel.data.interfaces.repository.IStorageRepository
import org.reynhart.baksotsel.models.LoginUserModel

class StorageRepository(
    private val storageProvider: ILocalStorage,
    private val dbStorageProvider: IDbStorage
): IStorageRepository {

    override suspend fun storeUserData(data: LoginUserModel) {
        storageProvider.setUserData(data)
        dbStorageProvider.storeData(data)
    }

    override suspend fun getUserData(): LoginUserModel? {
        return storageProvider.getUserData()
    }

    override suspend fun clearUserData(data:LoginUserModel) {
        storageProvider.clearUserData()
        dbStorageProvider.deleteData(data)
    }

    override suspend fun getUserDataStream(): Flow<List<LoginUserModel>> {
        val flow = dbStorageProvider.getDataStream()
        return flow
    }


}