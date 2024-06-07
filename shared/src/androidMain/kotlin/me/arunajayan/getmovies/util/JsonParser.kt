package me.arunajayan.getmovies.util

import com.google.gson.Gson

actual fun toJson(file: String): String {
    return file.toJsonString()
}

fun Any?.toJsonString():String{
    try {
        this?.let {
            return Gson().toJson(it)
        }
        return "{}"
    }catch (e:Exception ){
        return "{}"
    }
}