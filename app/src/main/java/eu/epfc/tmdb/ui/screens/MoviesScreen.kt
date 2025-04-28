package eu.epfc.tmdb.ui.screens



import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.epfc.tmdb.ui.components.MovieCard
import eu.epfc.tmdb.ui.components.Paging
import eu.epfc.tmdb.ui.components.TmdbScaffold
import eu.epfc.tmdb.ui.TmdbViewModelProvider




@Composable
fun MoviesScreen (
    navigateToMovieDetail: (Int) -> Unit,
    navigateToFavorites:() -> Unit,
    viewModel: MoviesViewModel = viewModel(factory = TmdbViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {

    TmdbScaffold(
        title = "Movie List screen",
        canNavigateNext = true,
        navigationDown = navigateToFavorites
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
            item {
                Paging(viewModel)
            }
        }
    }
}







