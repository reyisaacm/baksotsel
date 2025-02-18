package org.reynhart.baksotsel.data.repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Instant
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
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
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class StorageRepositoryTest: KoinTest {
    private lateinit var storageRepository :IStorageRepository

    @BeforeTest
    fun before(){
        stopKoin()
        startKoin {
            modules(
                module {
                    single<ILocalStorage>{ MockKStoreStorage() }
                    single<IDbStorage>{ MockSupabase() }

                    single<IStorageRepository> { StorageRepository(get(), get()) }
                })
        }
        storageRepository = get<IStorageRepository>()
    }

    @AfterTest
    fun after(){
        stopKoin()
    }

    @Test
    fun testStoreClearGetUserData() = runBlocking{



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
        var storageData = storageRepository.getUserData()
        var storageListDataStream :List<LoginUserModel> = storageRepository.getUserDataStream().first()
        var storageDataStream = storageListDataStream.first{x->x.id=="cd278376-d21b-4c05-975d-b64988bc98f4"}
        assertEquals(loginUserModel, storageData)
        assertEquals(loginUserModel, storageDataStream)

        storageRepository.clearUserData(loginUserModel)
        storageData = storageRepository.getUserData()
        storageListDataStream = storageRepository.getUserDataStream().first()
        storageDataStream = storageListDataStream.first{x->x.id=="cd278376-d21b-4c05-975d-b64988bc98f4"}
        assertEquals(null,storageData)
        assertEquals(false,storageDataStream.isActive)
    }

    @Test
    fun testSendUserLocation() = runBlocking {

        val loginUserModel = LoginUserModel(
            id = "cb120916-e91e-479e-a86a-be45205c533f",
            name = "User 1",
            type = "c",
            currentCoordinateLat = 1.0,
            currentCoordinateLong = 1.0,
            lastUpdate = Instant.fromEpochMilliseconds(1739865891924),
            isActive = true
        )

        storageRepository.storeUserData(loginUserModel)
        storageRepository.sendUserLocation(
            id="cb120916-e91e-479e-a86a-be45205c533f",
            latitude = 2.0,
            longitude = 2.0
            )

        val expectedModel = LoginUserModel(
            id = "cb120916-e91e-479e-a86a-be45205c533f",
            name = "User 1",
            type = "c",
            currentCoordinateLat = 2.0,
            currentCoordinateLong = 2.0,
            lastUpdate = Instant.fromEpochMilliseconds(1739865891924),
            isActive = true
        )

        val storageListDataStream :List<LoginUserModel> = storageRepository.getUserDataStream().first()
        val storageDataStream = storageListDataStream.first{x->x.id=="cb120916-e91e-479e-a86a-be45205c533f"}

        assertEquals(expectedModel, storageDataStream)
    }

    @Test
    fun testSendLastUpdate() = runBlocking {
        val loginUserModel = LoginUserModel(
            id = "ba40deb8-c60a-46b6-bf94-36f5a65bc7e9",
            name = "User 1",
            type = "c",
            currentCoordinateLat = 1.0,
            currentCoordinateLong = 1.0,
            lastUpdate = Instant.fromEpochMilliseconds(1739865892924),
            isActive = true
        )

        storageRepository.storeUserData(loginUserModel)
        storageRepository.sendLastUpdate(
            id="ba40deb8-c60a-46b6-bf94-36f5a65bc7e9",
            timestamp = Instant.fromEpochMilliseconds(1739865892924)
        )

        val storageListDataStream :List<LoginUserModel> = storageRepository.getUserDataStream().first()
        val storageDataStream = storageListDataStream.first { x->x.id=="ba40deb8-c60a-46b6-bf94-36f5a65bc7e9" }

        assertEquals(loginUserModel, storageDataStream)
    }
}