package me.arunajayan.getmovies.android

import android.app.Application
import me.arunajayan.getmovies.android.di.appModule
import me.arunajayan.getmovies.di.getSharedModules
import org.koin.core.context.startKoin

class GetMovies : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
        }
    }
}