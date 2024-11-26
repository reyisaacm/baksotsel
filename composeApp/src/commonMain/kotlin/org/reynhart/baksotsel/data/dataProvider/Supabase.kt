package org.reynhart.baksotsel.data.dataProvider

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.realtime.Realtime
import org.reynhart.baksotsel.data.interfaces.dataProvider.IDbStorage
import org.reynhart.baksotsel.models.LoginUserModel


class Supabase: IDbStorage {
    private val supabase = createSupabaseClient(
        supabaseUrl = "",
        supabaseKey = ""
    ){
        install(Postgrest)
        install(Realtime)
    }

    override suspend fun storeData(data: LoginUserModel){
        supabase.from("user").insert(data)
    }
}