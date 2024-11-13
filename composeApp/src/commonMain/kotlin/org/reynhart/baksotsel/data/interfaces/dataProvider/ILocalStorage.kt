package org.reynhart.baksotsel.data.interfaces.dataProvider

import org.reynhart.baksotsel.models.LoginUserModel

interface ILocalStorage {
    suspend fun setUserData(data: LoginUserModel)
    suspend fun getUserData():LoginUserModel?
}