package eu.epfc.tmdb.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import eu.epfc.tmdb.Constants
import eu.epfc.tmdb.R

@Composable
fun Poster(
    imagePath: String,
    size: Constants.TMDB_IMAGE_SIZE = Constants.TMDB_IMAGE_SIZE.MEDIUM,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {

    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(size.path + imagePath)
            .crossfade(true)
            .build(),
        error = painterResource(id= R.drawable.ic_launcher_background),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )

}

@Preview
@Composable
private fun PosterPreview() {
    val imagePath = "/fWPgbnt2LSqkQ6cdQc0SZN9CpLm.jpg"
    Column {
        Constants.TMDB_IMAGE_SIZE.entries.forEach {
           Poster(imagePath = imagePath, size = it)
        }
    }
}