package eu.epfc.tmdb.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.epfc.tmdb.data.Mock

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Genres(
    genres: List<String>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        genres.forEach { genre ->
            Surface(
                color = MaterialTheme.colorScheme.primary,
                shape =  MaterialTheme.shapes.large,
                shadowElevation = 40.dp
            ) {

            Text(
                text = genre,
                modifier = Modifier
                    .padding( horizontal = 8.dp, vertical = 4.dp)
            )
            }
        }
    }
}

@Preview
@Composable
private fun GenresPreview() {
    val genres: List<String> = Mock.genres.map { it.name }
    Genres(genres = genres)
}