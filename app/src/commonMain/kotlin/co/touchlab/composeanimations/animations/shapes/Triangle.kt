package co.touchlab.composeanimations.animations.shapes

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Triangle(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    backgroundColor: Color = Color(0xFFDDDDDD),
) {

    Box(
        modifier = modifier
            .width(size)
            .height(size)
            .clipToBounds()
            .background(backgroundColor, TriangleShape)
    )
}

private val TriangleShape = GenericShape { size, _ ->
    moveTo(size.width / 2f, 0f)
    lineTo(size.width, size.height)
    lineTo(0f, size.height)
}

@Composable
@Preview
fun TrianglePreview() {
    Triangle()
}
