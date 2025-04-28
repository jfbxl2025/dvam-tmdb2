package eu.epfc.tmdb.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.epfc.tmdb.Constants
import eu.epfc.tmdb.data.Mock
import eu.epfc.tmdb.data.model.Details
import eu.epfc.tmdb.data.model.Favorite
import eu.epfc.tmdb.ui.TmdbViewModelProvider
import eu.epfc.tmdb.ui.components.Genres
import eu.epfc.tmdb.ui.components.Poster
import eu.epfc.tmdb.ui.components.TmdbScaffold

@Composable
fun DetailsScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = viewModel(factory = TmdbViewModelProvider.Factory)
) {
    val details = viewModel.details
    val isFavorite = viewModel.isFavorite

    TmdbScaffold(
        title = "Detail Movie",
        canNavigateBack = true,
        navigateUp = navigateBack
    ) { innerPadding ->
        DetailsContent(
            details = details,
            isFavorite = isFavorite,
            modifier = modifier.padding(innerPadding),
            setFavorite = { viewModel.setFavorite() },
            navigateBack = navigateBack
        )
    }

}


@Composable
fun DetailsContent(
    details: Details,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    setFavorite: () -> Unit = {},
    navigateBack: () -> Unit = {}
) {


    Column(modifier = modifier.padding(horizontal = 16.dp))
    {
        Text(
            text = details.title,
            style = MaterialTheme.typography.headlineLarge
        )
        Poster (
            imagePath = details.posterPath ?: "",
            size = Constants.TMDB_IMAGE_SIZE.LARGE
        )
        Genres(
            genres = details.genres.map { it.name },
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text( text = details.overview )
        if(isFavorite) {
            Text(text="Favorite")
        }
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Button(
                onClick = {
                    setFavorite()
                    navigateBack()
                }
            ) {
                Text(text = if(isFavorite) "Remove from favorites" else "Add to favorites")
            }
        }
    }
}
@Preview
@Composable
private fun DetailsScreenPreview() {
    TmdbScaffold(
        title = "Detail Movie",
        canNavigateBack = true,
        navigateUp = { }
    ) { innerPadding ->
        DetailsContent(
            details = Mock.details,
            isFavorite = true,
            modifier = Modifier.padding(innerPadding),
            setFavorite = {},
            navigateBack = {}
        )
    }
}