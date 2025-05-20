package eu.epfc.tmdb.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private fun colorScore(score: Int) : Color =
    when(score) {
        in 1..25 -> Color.Red
        in 26..50 -> Color(255,128,0)
        in 51 .. 75 -> Color.Yellow
        in 76 .. 100 -> Color.Green
        else -> Color.Black
    }

@Composable
fun ScoreBadge(
    score: Int = 0,
    modifier: Modifier = Modifier
) {

    Box(
        Modifier
            .size(48.dp)
            .drawWithCache {
                onDrawBehind {
                    drawArc(
                        color = colorScore(score),
                        startAngle = -90F,
                        sweepAngle = 360 * score / 100f,
                        useCenter = false,
                        topLeft= Offset(5F, 5F),//Offset.Zero,
                        size = Size(width = size.width - 10, size.height - 10),
                        style = Stroke(width = 10f, cap = StrokeCap.Round),

                    )
                }
            },

    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),

        ) {
        Text(
            text = score.toString(),

            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
            Text(
                text="%",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 8.sp,
                modifier = Modifier.padding(1.dp)
                )
        }
    }
}


@Preview
@Composable
private fun ScoreBadgePreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
    ScoreBadge(0)
    ScoreBadge(24)
    ScoreBadge(48)
    ScoreBadge(68)
    ScoreBadge(78)
    }
}