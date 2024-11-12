package org.reynhart.baksotsel.data.interfaces.dataProvider

import org.reynhart.baksotsel.models.LoginUserModel

interface ILocalStorage {
    fun setUserData(data: LoginUserModel)
}