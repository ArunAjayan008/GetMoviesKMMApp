package me.arunajayan.getmovies.domain.usecase

import me.arunajayan.getmovies.domain.model.Movie
import me.arunajayan.getmovies.domain.repository.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMoviesUseCase : KoinComponent {
    private val repository: MovieRepository by inject()

    suspend operator fun invoke(page: Int): List<Movie> {
        return repository.getMovies(page = page)
    }
}