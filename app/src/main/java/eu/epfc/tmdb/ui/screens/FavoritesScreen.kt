package eu.epfc.tmdb.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

import eu.epfc.tmdb.ui.TmdbViewModelProvider
import eu.epfc.tmdb.ui.components.MovieCard
import eu.epfc.tmdb.ui.components.MoviesList
import eu.epfc.tmdb.ui.components.Paging
import eu.epfc.tmdb.ui.components.TmdbScaffold




@Composable
fun FavoritesScreen (
    navigateBack: () -> Unit,
    navigateToMovieDetail: (Int) -> Unit,
    viewModel: FavoritesViewModel = viewModel(factory = TmdbViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {

    TmdbScaffold(
        title = "Favorites screen ${viewModel.movies.count()}",
        canNavigateBack = true,
        navigateUp = navigateBack
    ) {
        val movies = viewModel.movies
        LazyColumn(
            modifier = modifier.padding(it)
        ) {
            items(movies) { movie ->
                MovieCard(
                    movie = movie,
                    onMovieClick = {navigateToMovieDetail(movie.movieId)}
                )
            }
        }
    }
}





