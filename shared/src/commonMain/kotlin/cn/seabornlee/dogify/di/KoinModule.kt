package cn.seabornlee.dogify.di

import cn.seabornlee.dogify.api.BreedsApi
import cn.seabornlee.dogify.database.createDriver
import cn.seabornlee.dogify.db.DogifyDatabase
import cn.seabornlee.dogify.model.FetchBreedsUseCase
import cn.seabornlee.dogify.model.GetBreedsUseCase
import cn.seabornlee.dogify.model.ToggleFavouriteStateUseCase
import cn.seabornlee.dogify.repository.BreedsLocalSource
import cn.seabornlee.dogify.repository.BreedsRemoteSource
import cn.seabornlee.dogify.repository.BreedsRepository
import cn.seabornlee.dogify.util.getDispatcherProvider
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val utilityModule = module {
    factory { getDispatcherProvider() }
    single { DogifyDatabase(createDriver("dogify.db")) }
}

private val apiModule = module {
    factory { BreedsApi() }
}

private val repositoryModule = module {
    single { BreedsRepository() }
    factory { BreedsRemoteSource(get(), get()) }
    factory { BreedsLocalSource(get(), get()) }
}

private val usecaseModule = module {
    factory { GetBreedsUseCase() }
    factory { FetchBreedsUseCase() }
    factory { ToggleFavouriteStateUseCase() }
}

private val sharedModules = listOf(usecaseModule, repositoryModule, apiModule, utilityModule)
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(sharedModules)
}

fun initKoin() = initKoin {}