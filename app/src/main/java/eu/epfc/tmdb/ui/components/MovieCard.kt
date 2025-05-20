package eu.epfc.tmdb.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.epfc.tmdb.Constants
import eu.epfc.tmdb.data.Mock
import eu.epfc.tmdb.data.model.Movie


@Composable
fun MovieCard(
    movie: Movie,
    onMovieClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    showIsFavorite: Boolean = true,
    content: @Composable () ->  Unit = {}
) {

    Card(
        modifier = modifier
            .clickable { onMovieClick() },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

    ) {
        Column (
            modifier = modifier.padding(all = 16.dp)
        ) {
            Text(
                text = movie.title,
                modifier = Modifier.padding(bottom = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
            Row(
                modifier = Modifier.padding(bottom = 16.dp),
            ) {

                Poster(
                    imagePath = movie.posterPath ?: "",
                    size = Constants.TMDB_IMAGE_SIZE.SMALL
                )
                Text(
                    text = movie.overview,
                    maxLines = 8,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
               ScoreBadge( score = (movie.voteAverage * 10).toInt())
                if(showIsFavorite && movie.isFavorite)
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
            content()
        }
    }
}

@Preview
@Composable
private fun MovieCardPreview() {


    MovieCard(movie = Mock.movie)
}