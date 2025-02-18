package org.reynhart.baksotsel.data.dataProvider

import org.reynhart.baksotsel.data.interfaces.dataProvider.ILocalStorage
import org.reynhart.baksotsel.models.LoginUserModel

var store : LoginUserModel? = null
class MockKStoreStorage: ILocalStorage {
    override suspend fun setUserData(data: LoginUserModel) {
        store = data
    }

    override suspend fun getUserData(): LoginUserModel? {
        return store
    }

    override suspend fun clearUserData() {
        store=null
    }
}