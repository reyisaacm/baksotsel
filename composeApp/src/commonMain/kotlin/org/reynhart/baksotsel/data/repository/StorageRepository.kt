package org.reynhart.baksotsel.data.repository

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

    override suspend fun clearUserData() {
        storageProvider.clearUserData()
    }


}