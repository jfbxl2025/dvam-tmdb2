package eu.epfc.tmdb.ui.screens


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

import eu.epfc.tmdb.ui.TmdbViewModelProvider
import eu.epfc.tmdb.ui.components.MovieCard
import eu.epfc.tmdb.ui.components.TmdbScaffold




@Composable
fun FavoritesScreen (
    navigateBack: () -> Unit,
    navigateToMovieDetail: (Int) -> Unit,
    viewModel: FavoritesViewModel = viewModel(factory = TmdbViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {

    TmdbScaffold(
        title = "Favorites",
        canNavigateBack = true,
        navigateUp = navigateBack
    ) {
        val movies = remember {  viewModel.movies }
        LazyColumn(
            modifier = modifier.padding(it)
        ) {
            items(items = movies) { movie ->
                MovieCard(
                    movie = movie,
                    onMovieClick = {navigateToMovieDetail(movie.movieId)}
                )
            }
        }
    }
}





