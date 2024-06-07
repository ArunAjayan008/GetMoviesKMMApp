package me.arunajayan.getmovies.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MoviesResponse(
    @SerialName("results")
    val results: List<MovieRemote>
)