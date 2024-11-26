package org.reynhart.baksotsel.data.interfaces.dataProvider

import org.reynhart.baksotsel.models.LoginUserModel

interface IDbStorage {
    suspend fun storeData(data: LoginUserModel)
}