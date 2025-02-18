package org.reynhart.baksotsel.data.dataProvider

import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Instant
import org.reynhart.baksotsel.data.interfaces.dataProvider.IDbStorage
import org.reynhart.baksotsel.models.LoginUserModel

var userList :MutableList<LoginUserModel> = mutableListOf<LoginUserModel>()

class MockSupabase: IDbStorage {
    override suspend fun storeData(data: LoginUserModel){
        userList.add(data)
    }

    override suspend fun deleteData(data: LoginUserModel) {
        userList.find{ x-> x.id == data.id }?.isActive = true
    }

    @OptIn(SupabaseExperimental::class)
    override suspend fun getDataStream(): Flow<List<LoginUserModel>> {
        val flowData: Flow<List<LoginUserModel>> = flow{
            emit(userList)
        }
        return flowData

    }

    override suspend fun updateLocationData(id: String, latitude: Double, longitude: Double) {

        userList.find{ x-> x.id == id }?.currentCoordinateLat = latitude
        userList.find{ x-> x.id == id }?.currentCoordinateLong = longitude
    }

    override suspend fun updateLastUpdate(id: String, timestamp: Instant) {
        userList.find{ x-> x.id == id }?.lastUpdate = timestamp
    }
}