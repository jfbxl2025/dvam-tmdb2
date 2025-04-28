package eu.epfc.tmdb.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun ScoreBadge(
    score: Int = 0,
    modifier: Modifier = Modifier
) {

    val textMeasurer = rememberTextMeasurer()
    Text(
        text = score.toString(),
        modifier = Modifier
            .drawWithCache {
                val measuredText =
                    textMeasurer.measure(
                        AnnotatedString(score.toString()),
                        constraints = Constraints.fixedWidth((size.width * 2f / 3f).toInt()),
                        style = TextStyle(fontSize = 18.sp)
                    )

                onDrawBehind {
                    drawRect(color = Color.Cyan, size = measuredText.size.toSize())
//                    drawText(measuredText)
                }
            }
//            .fillMaxSize()

    )
}


@Preview
@Composable
private fun ScoreBadgePreview() {

    ScoreBadge(76)
}