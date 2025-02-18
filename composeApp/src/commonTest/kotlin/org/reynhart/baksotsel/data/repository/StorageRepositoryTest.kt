package org.reynhart.baksotsel.data.repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Instant
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.reynhart.baksotsel.data.dataProvider.KStoreStorage
import org.reynhart.baksotsel.data.dataProvider.MockKStoreStorage
import org.reynhart.baksotsel.data.dataProvider.MockSupabase
import org.reynhart.baksotsel.data.dataProvider.Supabase
import org.reynhart.baksotsel.data.interfaces.dataProvider.IDbStorage
import org.reynhart.baksotsel.data.interfaces.dataProvider.ILocalStorage
import org.reynhart.baksotsel.data.interfaces.repository.IStorageRepository
import org.reynhart.baksotsel.models.LoginUserModel
import kotlin.test.Test
import kotlin.test.assertEquals

class StorageRepositoryTest: KoinTest {

    init {
        startKoin {
            modules(
                module {
                    single<ILocalStorage>{ MockKStoreStorage() }
                    single<IDbStorage>{ MockSupabase() }

                    single<IStorageRepository> { StorageRepository(get(), get()) }
                })
        }
    }

    @Test
    fun testStoreAndGetUserData() = runBlocking{

        val storageRepository = get<IStorageRepository>()

        val loginUserModel = LoginUserModel(
            id = "cd278376-d21b-4c05-975d-b64988bc98f4",
            name = "User 1",
            type = "c",
            currentCoordinateLat = 1.0,
            currentCoordinateLong = 1.0,
            lastUpdate = Instant.fromEpochMilliseconds(1739865891924),
            isActive = true
        )

        storageRepository.storeUserData(loginUserModel)
        val storageData = storageRepository.getUserData()
        val storageListDataStream :List<LoginUserModel> = storageRepository.getUserDataStream().first()
        val storageDataStream = storageListDataStream.first()
        assertEquals(loginUserModel, storageData)
        assertEquals(loginUserModel, storageDataStream)
    }
}