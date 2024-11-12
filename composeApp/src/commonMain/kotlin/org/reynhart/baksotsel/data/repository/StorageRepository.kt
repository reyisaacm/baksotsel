package org.reynhart.baksotsel.data.repository

import org.reynhart.baksotsel.data.interfaces.dataProvider.ILocalStorage
import org.reynhart.baksotsel.data.interfaces.repository.IStorageRepository
import org.reynhart.baksotsel.models.LoginUserModel

class StorageRepository(
    private val storageProvider: ILocalStorage
): IStorageRepository {

    override fun storeData(data: LoginUserModel) {
        storageProvider.setUserData(data)
    }
}