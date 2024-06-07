package me.arunajayan.getmovies.domain.repository

import me.arunajayan.getmovies.domain.model.Movie

internal interface MovieRepository {
    suspend fun getMovies(page: Int): List<Movie>

    suspend fun getMovie(movieId: Int): Movie
}