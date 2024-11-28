package org.reynhart.baksotsel.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.reynhart.baksotsel.data.dataProvider.KStoreStorage
import org.reynhart.baksotsel.data.dataProvider.Supabase
import org.reynhart.baksotsel.data.interfaces.dataProvider.IDbStorage
import org.reynhart.baksotsel.data.interfaces.dataProvider.ILocalStorage
import org.reynhart.baksotsel.data.interfaces.repository.IStorageRepository
import org.reynhart.baksotsel.data.repository.StorageRepository
import org.reynhart.baksotsel.viewmodels.LoginViewModel
import org.reynhart.baksotsel.viewmodels.MainViewModel

val sharedModule= module {
    single<ILocalStorage>{KStoreStorage()}
    single<IDbStorage>{Supabase()}

    single<IStorageRepository> { StorageRepository(get(), get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get()) }

}
