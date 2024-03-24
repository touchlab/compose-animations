package co.touchlab.compose.value.animator

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.delay

@Composable
fun animateFloatValuesAsState(
    vararg values: Float,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Float> = animateValuesAsState(
    values = values.toTypedArray(),
    getValueAtFraction = ValueAtFraction.Float,
    startDelay = startDelay,
    animationSpec = animationSpec,
)

@Composable
fun animateIntValuesAsState(
    values: Array<Int>,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Int> = animateValuesAsState(
    values = values,
    getValueAtFraction = ValueAtFraction.Int,
    startDelay = startDelay,
    animationSpec = animationSpec,
)

@Composable
fun animateDpValuesAsState(
    values: Array<Dp>,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Dp> = animateValuesAsState(
    values = values,
    getValueAtFraction = ValueAtFraction.Dp,
    startDelay = startDelay,
    animationSpec = animationSpec,
)

@Composable
fun animateSizeValuesAsState(
    values: Array<Size>,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Size> = animateValuesAsState(
    values = values,
    getValueAtFraction = ValueAtFraction.Size,
    startDelay = startDelay,
    animationSpec = animationSpec,
)

@Composable
fun animateOffsetValuesAsState(
    values: Array<Offset>,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Offset> = animateValuesAsState(
    values = values,
    getValueAtFraction = ValueAtFraction.Offset,
    startDelay = startDelay,
    animationSpec = animationSpec,
)

@Composable
fun animateRectValuesAsState(
    values: Array<Rect>,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Rect> = animateValuesAsState(
    values = values,
    getValueAtFraction = ValueAtFraction.Rect,
    startDelay = startDelay,
    animationSpec = animationSpec,
)

@Composable
fun animateIntOffsetValuesAsState(
    values: Array<IntOffset>,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<IntOffset> = animateValuesAsState(
    values = values,
    getValueAtFraction = ValueAtFraction.IntOffset,
    startDelay = startDelay,
    animationSpec = animationSpec,
)

@Composable
fun animateIntSizeValuesAsState(
    values: Array<IntSize>,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<IntSize> = animateValuesAsState(
    values = values,
    getValueAtFraction = ValueAtFraction.IntSize,
    startDelay = startDelay,
    animationSpec = animationSpec,
)

@Composable
fun animateColorValuesAsState(
    values: Array<Color>,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Color> = animateValuesAsState(
    values = values,
    getValueAtFraction = ValueAtFraction.Color,
    startDelay = startDelay,
    animationSpec = animationSpec,
)

@Composable
fun <T> animateValuesAsState(
    vararg values: T,
    getValueAtFraction: ValueAtFraction<T>,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<T> {
    require(values.isNotEmpty()) {
        "You should provide at least one item to animate"
    }

    val valueAnimator by rememberUpdatedState(
        MultipleValuesAnimator(values.toList())
    )

    val animatedValue = remember(valueAnimator) {
        mutableStateOf(valueAnimator.initialAnimationValue)
    }

    LaunchedEffect(valueAnimator) {
        if (startDelay > 0) {
            delay(startDelay)
        }

        val (_, setValue) = animatedValue
        animate(
            initialValue = valueAnimator.initialFrameValue,
            targetValue = valueAnimator.targetFrameValue,
            animationSpec = animationSpec,
        ) { frame, _ -> setValue(valueAnimator.animate(frame, getValueAtFraction)) }
    }

    return animatedValue
}
