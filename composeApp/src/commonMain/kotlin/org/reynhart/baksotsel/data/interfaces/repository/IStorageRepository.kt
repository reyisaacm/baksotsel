package org.reynhart.baksotsel.data.interfaces.repository

import org.reynhart.baksotsel.models.LoginUserModel

interface IStorageRepository {
    fun storeData(data: LoginUserModel)
}