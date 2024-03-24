package co.touchlab.compose.animations.shapes

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Ball(
    modifier: Modifier = Modifier,
    size: Dp = 12.dp,
    backgroundColor: Color = Color(0xFFDDDDDD),
) {
    Box(
        modifier = modifier
            .width(size)
            .height(size)
            .clipToBounds()
            .background(backgroundColor, CircleShape)
    )
}

@Preview
@Composable
fun BallPreview() {
    Ball(
        size = 32.dp,
        backgroundColor = Color(0xFF03DAC5),
    )
}
