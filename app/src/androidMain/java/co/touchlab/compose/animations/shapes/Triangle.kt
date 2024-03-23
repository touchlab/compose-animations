package co.touchlab.compose.animations.shapes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.touchlab.compose.animations.R

@Composable
fun Triangle(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    backgroundColor: Color = colorResource(id = R.color.ball_gray),
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
