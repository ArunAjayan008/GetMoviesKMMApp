package me.arunajayan.getmovies.viewModels

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.stateIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import me.arunajayan.getmovies.domain.model.Movie
import me.arunajayan.getmovies.domain.usecase.GetMoviesUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class TestViewmodel:ViewModel(),KoinComponent {
    private val getMoviesUseCase: GetMoviesUseCase by inject()

    @NativeCoroutinesState
    var movieList: StateFlow<List<Movie>> = getMoviesFlow(1)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

//    fun getObjects(): Flow<List<Movie>> = getMoviesUseCase(page = currentPage)

    fun getMoviesFlow(page: Int): Flow<List<Movie>> = flow {
        val resultMovies = getMoviesUseCase(page = page)
        emit(resultMovies)
    }

//    private suspend fun getObjects(scope: CoroutineScope, currentPage: Int): StateFlow<List<Movie>> {
//        return getMoviesUseCase(page = currentPage)
//            .stateIn(
//                scope = scope,
//                started = SharingStarted.WhileSubscribed(5000),
//                initialValue = emptyList()
//            )
//    }

}