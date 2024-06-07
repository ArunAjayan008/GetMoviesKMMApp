package me.arunajayan.getmovies.domain.usecase

import me.arunajayan.getmovies.domain.model.Movie
import me.arunajayan.getmovies.domain.repository.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMovieUseCase : KoinComponent {
    private val repository: MovieRepository by inject()

    suspend operator fun invoke(movieId: Int): Movie {
        return repository.getMovie(movieId = movieId)
    }
}