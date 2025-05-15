package eu.epfc.tmdb.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eu.epfc.tmdb.data.model.Movie

@Composable
fun MoviesList(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
    card: @Composable (Movie) ->  Unit,
    paging:  @Composable () ->  Unit = {}
) {
    
    LazyColumn(
        modifier = modifier
    ) {
        items(movies) { movie ->
            card(movie)
        }
        item {
           paging()
        }

    }
}
