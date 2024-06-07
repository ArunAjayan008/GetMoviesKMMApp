package me.arunajayan.getmovies.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class IoDispatcher:Dispatcher{
    override val io: CoroutineDispatcher
        get() = Dispatchers.Default
}

internal actual fun providesDispatcher():Dispatcher = IoDispatcher()