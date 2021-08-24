package co.touchlab.compose.value.animator

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

@Composable
fun InfiniteTransition.animateFloatValues(
    vararg values: Float,
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(animation = tween()),
): State<Float> = animateValues(
    values = values.toTypedArray(),
    getValueAtFraction = ValueAtFraction.Float,
    animationSpec = animationSpec,
)

@Composable
fun InfiniteTransition.animateIntValues(
    values: Array<Int>,
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(animation = tween()),
): State<Int> = animateValues(
    values = values,
    getValueAtFraction = ValueAtFraction.Int,
    animationSpec = animationSpec,
)

@Composable
fun InfiniteTransition.animateDpValues(
    values: Array<Dp>,
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(animation = tween()),
): State<Dp> = animateValues(
    values = values,
    getValueAtFraction = ValueAtFraction.Dp,
    animationSpec = animationSpec,
)

@Composable
fun InfiniteTransition.animateSizeValues(
    values: Array<Size>,
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(animation = tween()),
): State<Size> = animateValues(
    values = values,
    getValueAtFraction = ValueAtFraction.Size,
    animationSpec = animationSpec,
)

@Composable
fun InfiniteTransition.animateOffsetValues(
    values: Array<Offset>,
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(animation = tween()),
): State<Offset> = animateValues(
    values = values,
    getValueAtFraction = ValueAtFraction.Offset,
    animationSpec = animationSpec,
)

@Composable
fun InfiniteTransition.animateRectValues(
    values: Array<Rect>,
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(animation = tween()),
): State<Rect> = animateValues(
    values = values,
    getValueAtFraction = ValueAtFraction.Rect,
    animationSpec = animationSpec,
)

@Composable
fun InfiniteTransition.animateIntOffsetValues(
    values: Array<IntOffset>,
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(animation = tween()),
): State<IntOffset> = animateValues(
    values = values,
    getValueAtFraction = ValueAtFraction.IntOffset,
    animationSpec = animationSpec,
)

@Composable
fun InfiniteTransition.animateIntSizeValues(
    values: Array<IntSize>,
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(animation = tween()),
): State<IntSize> = animateValues(
    values = values,
    getValueAtFraction = ValueAtFraction.IntSize,
    animationSpec = animationSpec,
)

@Composable
fun InfiniteTransition.animateColorValues(
    values: Array<Color>,
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(animation = tween()),
): State<Color> = animateValues(
    values = values,
    getValueAtFraction = ValueAtFraction.Color,
    animationSpec = animationSpec,
)

@Composable
fun <T> InfiniteTransition.animateValues(
    vararg values: T,
    getValueAtFraction: ValueAtFraction<T>,
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(animation = tween()),
): State<T> {
    require(values.isNotEmpty()) {
        "You should provide at least one item to animate"
    }

    val valueAnimator by rememberUpdatedState(
        MultipleValuesAnimator(values.toList())
    )

    val animatedFrame by animateFloat(
        initialValue = valueAnimator.initialFrameValue,
        targetValue = valueAnimator.targetFrameValue,
        animationSpec = animationSpec,
    )

    return remember(valueAnimator) {
        derivedStateOf {
            valueAnimator.animate(animatedFrame, getValueAtFraction)
        }
    }
}
