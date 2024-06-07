package me.arunajayan.getmovies.di

import me.arunajayan.getmovies.data.remote.MovieService
import me.arunajayan.getmovies.data.remote.RemoteDataSource
import me.arunajayan.getmovies.data.repository.MovieRepositoryImpl
import me.arunajayan.getmovies.domain.repository.MovieRepository
import me.arunajayan.getmovies.domain.usecase.GetMovieUseCase
import me.arunajayan.getmovies.domain.usecase.GetMoviesUseCase
import me.arunajayan.getmovies.util.providesDispatcher
import org.koin.dsl.module

val dataModule = module {
    factory { RemoteDataSource(get(), get()) }
    factory { MovieService() }
}

private val utilityModule = module {
    factory { providesDispatcher() }
}

private val domainModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    factory { GetMoviesUseCase() }
    factory { GetMovieUseCase() }
}

private val sharedModules = listOf(domainModule, dataModule, utilityModule)

fun getSharedModules() = sharedModules
