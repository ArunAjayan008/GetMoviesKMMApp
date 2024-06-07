package me.arunajayan.getmovies.data.repository

import kotlinx.coroutines.flow.Flow
import me.arunajayan.getmovies.data.remote.RemoteDataSource
import me.arunajayan.getmovies.data.util.toMovie
import me.arunajayan.getmovies.domain.model.Movie
import me.arunajayan.getmovies.domain.repository.MovieRepository

internal class MovieRepositoryImpl(
    val remoteDateSource: RemoteDataSource
) : MovieRepository {

    override suspend fun getMovies(page: Int): List<Movie> {
        return remoteDateSource.getMovies(page = page).results.map {
            it.toMovie()
        }
    }

    override suspend fun getMovie(movieId: Int): Movie {
        return remoteDateSource.getMovie(movieId = movieId).toMovie()
    }
}