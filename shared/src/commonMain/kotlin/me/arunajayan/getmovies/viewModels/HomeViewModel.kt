package me.arunajayan.getmovies.viewModels

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import com.rickclephas.kmp.observableviewmodel.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.arunajayan.getmovies.domain.model.Movie
import me.arunajayan.getmovies.domain.usecase.GetMoviesUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class HomeViewModel : ViewModel(), KoinComponent {

    private val getMoviesUseCase: GetMoviesUseCase by inject()

    private var currentPage = 1

    private val _uiState = MutableStateFlow(viewModelScope, HomeScreenState())

    @NativeCoroutinesState
    val uiState = _uiState.asStateFlow()

    init {
        loadMovies(forceReload = false)
    }

    fun loadMovies(forceReload: Boolean = false) {
        if (uiState.value.loading) return
        if (forceReload) currentPage = 1
        if (currentPage == 1) {
            _uiState.update { it.copy(refreshing = true) }
        }

        viewModelScope.coroutineScope.launch {
            _uiState.update { it.copy(loading = true) }
            try {
                val resultMovies = getMoviesUseCase(page = currentPage)
                val movies = if (currentPage == 1) resultMovies else uiState.value.movies + resultMovies
                currentPage += 1
                _uiState.update {
                    it.copy(
                        loading = false,
                        refreshing = false,
                        loadFinished = resultMovies.isEmpty(),
                        movies = movies
                    )
                }
            } catch (error: Throwable) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        refreshing = false,
                        loadFinished = true,
                        errorMessage = "Could not load movies: ${error.message}"
                    )
                }
            }
        }
    }

    data class HomeScreenState(
        val loading: Boolean = false,
        val refreshing: Boolean = false,
        val movies: List<Movie> = emptyList(),
        val errorMessage: String? = null,
        val loadFinished: Boolean = false
    )
}