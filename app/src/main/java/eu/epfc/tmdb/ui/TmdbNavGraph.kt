package eu.epfc.tmdb.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import eu.epfc.tmdb.ui.screens.FavoritesScreen
import eu.epfc.tmdb.ui.screens.HomeScreen
import eu.epfc.tmdb.ui.screens.DetailsScreen
import eu.epfc.tmdb.ui.screens.MoviesScreen
import kotlinx.serialization.Serializable

@Serializable object HomeDestination
@Serializable object MoviesDestination
@Serializable object FavoritesDestination
@Serializable data class DetailsDestination(val movieId: Int)

@Composable
fun TmdbNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination
    ) {
        composable<HomeDestination> {
           HomeScreen(
               navigateToConnected = { navController.navigate(MoviesDestination) }
           )
        }


        composable<MoviesDestination> {
            MoviesScreen(
                navigateToMovieDetail = { navController.navigate(DetailsDestination(movieId = it )) },
                navigateToFavorites = { navController.navigate(FavoritesDestination)}
            )
        }


        composable<DetailsDestination> {
            DetailsScreen(
                navigateBack = { navController.navigateUp() }
            )
        }

        composable<FavoritesDestination> {
            FavoritesScreen(
                navigateToMovieDetail = { navController.navigate(DetailsDestination(movieId = it )) },
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}