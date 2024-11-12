package org.reynhart.baksotsel.di

import org.koin.dsl.bind
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.reynhart.baksotsel.data.interfaces.repository.IStorageRepository
import org.reynhart.baksotsel.data.repository.StorageRepository

val sharedModule= module {
    single {
        StorageRepository(get())
    }.bind<IStorageRepository>()
}
