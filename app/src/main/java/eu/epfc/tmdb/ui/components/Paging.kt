package eu.epfc.tmdb.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.epfc.tmdb.ui.Paginated
import eu.epfc.tmdb.ui.theme.TmdbTheme

@Composable
fun Paging(
   paginated: Paginated,
    modifier: Modifier = Modifier
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (paginated.page > 1) {
            Button(
                onClick = { paginated.getPrev() },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "<")
            }
        }
        Text(
            text = "${paginated.page} / ${paginated.pagesCount}",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleLarge

        )
        if (paginated.hasNext ) {
            Button(
                onClick = { paginated.getNext() },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text=">")
            }
        }
    }
}


private data class Mock (
    override var page: Int,
    override var pagesCount: Int,
    ) : Paginated
{
    override fun getPrev() { }
    override fun getNext() { }
}

@Preview
@Composable
private fun PagingPrev() {
    Paging( Mock(page = 2, pagesCount = 3))
}