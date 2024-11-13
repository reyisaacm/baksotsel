package org.reynhart.baksotsel.data.dataProvider

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.extensions.storeOf
import kotlinx.io.files.Path
import org.reynhart.baksotsel.data.interfaces.dataProvider.ILocalStorage
import org.reynhart.baksotsel.models.LoginUserModel

class KStoreStorage :ILocalStorage{
    override suspend fun setUserData(data: LoginUserModel) {
        val store: KStore<LoginUserModel> = storeOf(file = Path("/bakso-tsel/user.json"), version = 0)
        store.set(data)
    }

    override suspend fun getUserData(): LoginUserModel? {
        val store: KStore<LoginUserModel> = storeOf(file = Path("/bakso-tsel/user.json"), version = 0)
        return store.get()
    }

}