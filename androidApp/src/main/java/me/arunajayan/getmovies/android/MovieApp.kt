package me.arunajayan.getmovies.android

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import me.arunajayan.getmovies.android.common.Detail
import me.arunajayan.getmovies.android.common.MovieAppBar
import me.arunajayan.getmovies.android.common.movieDestinations
import me.arunajayan.getmovies.android.detail.DetailScreen
import me.arunajayan.getmovies.android.detail.DetailViewModel
import me.arunajayan.getmovies.android.home.HomeScreen
import me.arunajayan.getmovies.viewModels.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieApp(
    uiState: HomeViewModel.HomeScreenState,
    loadNextMovies: (Boolean) -> Unit,
) {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = movieDestinations.find {
        backStackEntry?.destination?.route == it.route ||
                backStackEntry?.destination?.route == it.routeWithArgs
    }?: Home

    Scaffold(
        topBar = {
            MovieAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
            ) {
                navController.navigateUp()
            }
        }
    ) {innerPaddings ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPaddings),
            startDestination = me.arunajayan.getmovies.android.common.Home.routeWithArgs
        ){
            composable(me.arunajayan.getmovies.android.common.Home.routeWithArgs){
                HomeScreen(
                    uiState = uiState,
                    loadNextMovies = loadNextMovies,
                    navigateToDetail = {
                        navController.navigate(
                            "${Detail.route}/${it.id}"
                        )
                    }
                )
            }

            composable(Detail.routeWithArgs, arguments = Detail.arguments){
                val movieId = it.arguments?.getInt("movieId") ?: 0
                val detailViewModel: DetailViewModel = koinViewModel(
                    parameters = { parametersOf(movieId) }
                )

                DetailScreen(uiState = detailViewModel.uiState)
            }
        }

    }
}