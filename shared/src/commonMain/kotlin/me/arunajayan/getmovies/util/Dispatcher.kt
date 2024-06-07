package me.arunajayan.getmovies.util

import kotlinx.coroutines.CoroutineDispatcher

internal interface Dispatcher {
    val io: CoroutineDispatcher
}

internal expect fun providesDispatcher(): Dispatcher