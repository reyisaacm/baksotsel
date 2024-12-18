package org.reynhart.baksotsel.data.dataProvider

import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.filter.FilterOperation
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.Flow
import org.reynhart.baksotsel.EnvVar
import org.reynhart.baksotsel.data.interfaces.dataProvider.IDbStorage
import org.reynhart.baksotsel.models.LoginUserModel


class Supabase: IDbStorage {
    private val supabase = createSupabaseClient(
        supabaseUrl = EnvVar.SUPABASE_URL,
        supabaseKey = EnvVar.SUPABASE_KEY
    ){
        install(Postgrest)
        install(Realtime)
    }

    val userTableName = "user_test"

    override suspend fun storeData(data: LoginUserModel){
        supabase.from(userTableName).insert(data)
    }

    override suspend fun deleteData(data: LoginUserModel) {
        supabase.from(userTableName).update({set("isActive", false) }) {
            filter {
                LoginUserModel::id eq data.id
            }
        }
    }

    @OptIn(SupabaseExperimental::class)
    override suspend fun getDataStream(): Flow<List<LoginUserModel>> {
        val flow: Flow<List<LoginUserModel>> = supabase.from(userTableName).selectAsFlow(
            LoginUserModel::id,
        )
        return flow

    }
}