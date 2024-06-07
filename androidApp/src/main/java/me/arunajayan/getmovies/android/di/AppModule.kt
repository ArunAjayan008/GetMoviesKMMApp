package me.arunajayan.getmovies.android.di

import me.arunajayan.getmovies.android.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { params -> DetailViewModel(getMovieUseCase = get(), movieId = params.get()) }
}