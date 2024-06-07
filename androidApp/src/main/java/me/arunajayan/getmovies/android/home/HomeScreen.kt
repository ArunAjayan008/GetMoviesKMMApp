package me.arunajayan.getmovies.android.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import me.arunajayan.getmovies.domain.model.Movie
import me.arunajayan.getmovies.viewModels.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeViewModel.HomeScreenState,
    loadNextMovies: (Boolean) -> Unit,
    navigateToDetail: (Movie) -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Black)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            itemsIndexed(
                uiState.movies,
                key = {_, movie -> movie.id}
            ){index, movie ->
                MovieListItem(movie = movie, onMovieClick = { navigateToDetail(movie) })

                if (index >= uiState.movies.size -1 && !uiState.loading && !uiState.loadFinished){
                    LaunchedEffect(key1 = Unit, block = { loadNextMovies(false) })
                }
            }

            if (uiState.loading && uiState.movies.isNotEmpty()){
                item(span = { GridItemSpan(2) }){
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(
                            color = Red
                        )
                    }
                }
            }
        }

//        PullRefreshIndicator(
//            refreshing = uiState.refreshing,
//            state = pullRefreshState,
//            modifier = modifier.align(Alignment.TopCenter)
//        )
    }
}