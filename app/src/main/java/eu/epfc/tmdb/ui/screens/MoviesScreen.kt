package eu.epfc.tmdb.ui.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.epfc.tmdb.ui.components.MovieCard
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
        title = "Movies List",
        canNavigateNext = true,
        navigationDown = navigateToFavorites
    ) {
        val movies = viewModel.movies
        LazyColumn(
            modifier = modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(4.dp)

        ) {
            items(movies) { movie ->
                MovieCard(
                    movie = movie,
                    onMovieClick = {navigateToMovieDetail(movie.movieId)}
                )
            }
            item {
                NextButton( onClick = {viewModel.fetchMore()})
            }
        }
    }
}
@Composable
private fun NextButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        Text(
            text= "Show more",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
//            color = Color.White
        )
    }
}


@Preview
@Composable
private fun NextButtonPreview() {
   NextButton()
}






