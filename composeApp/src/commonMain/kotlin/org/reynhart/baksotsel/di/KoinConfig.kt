package org.reynhart.baksotsel.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.reynhart.baksotsel.data.dataProvider.KStoreStorage
import org.reynhart.baksotsel.data.interfaces.dataProvider.ILocalStorage
import org.reynhart.baksotsel.data.interfaces.repository.IStorageRepository
import org.reynhart.baksotsel.data.repository.StorageRepository
import org.reynhart.baksotsel.viewmodels.LoginViewModel

val sharedModule= module {
    single<ILocalStorage>{KStoreStorage()}

    single<IStorageRepository> { StorageRepository(get()) }

    viewModel { LoginViewModel(get()) }
}
