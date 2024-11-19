package org.reynhart.baksotsel.data.dataProvider

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.extensions.storeOf
import kotlinx.io.files.Path
import org.reynhart.baksotsel.data.interfaces.dataProvider.ILocalStorage
import org.reynhart.baksotsel.getFileDirectory
import org.reynhart.baksotsel.models.LoginUserModel

class KStoreStorage :ILocalStorage{
    private val fileDir = getFileDirectory()
    private val store: KStore<LoginUserModel> = storeOf(file = Path("${fileDir}/user.json"), version = 0)

    override suspend fun setUserData(data: LoginUserModel) {
        store.set(data)
    }

    override suspend fun getUserData(): LoginUserModel? {
        return store.get()
    }

    override suspend fun clearUserData() {
        store.delete()
    }

}