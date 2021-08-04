package co.touchlab.compose.value.animator

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
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
    delay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Float> = animateValuesAsState(
    values = values.toTypedArray(),
    getValueAtFrame = ValueAtFrame.Float,
    delay = delay,
    animationSpec = animationSpec,
)

@Composable
fun animateIntValuesAsState(
    values: Array<Int>,
    delay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Int> = animateValuesAsState(
    values = values,
    getValueAtFrame = ValueAtFrame.Int,
    delay = delay,
    animationSpec = animationSpec,
)

@Composable
fun animateDpValuesAsState(
    values: Array<Dp>,
    delay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Dp> = animateValuesAsState(
    values = values,
    getValueAtFrame = ValueAtFrame.Dp,
    delay = delay,
    animationSpec = animationSpec,
)

@Composable
fun animateSizeValuesAsState(
    values: Array<Size>,
    delay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Size> = animateValuesAsState(
    values = values,
    getValueAtFrame = ValueAtFrame.Size,
    delay = delay,
    animationSpec = animationSpec,
)

@Composable
fun animateOffsetValuesAsState(
    values: Array<Offset>,
    delay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Offset> = animateValuesAsState(
    values = values,
    getValueAtFrame = ValueAtFrame.Offset,
    delay = delay,
    animationSpec = animationSpec,
)

@Composable
fun animateRectValuesAsState(
    values: Array<Rect>,
    delay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Rect> = animateValuesAsState(
    values = values,
    getValueAtFrame = ValueAtFrame.Rect,
    delay = delay,
    animationSpec = animationSpec,
)

@Composable
fun animateIntOffsetValuesAsState(
    values: Array<IntOffset>,
    delay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<IntOffset> = animateValuesAsState(
    values = values,
    getValueAtFrame = ValueAtFrame.IntOffset,
    delay = delay,
    animationSpec = animationSpec,
)

@Composable
fun animateIntSizeValuesAsState(
    values: Array<IntSize>,
    delay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<IntSize> = animateValuesAsState(
    values = values,
    getValueAtFrame = ValueAtFrame.IntSize,
    delay = delay,
    animationSpec = animationSpec,
)

@Composable
fun animateColorValuesAsState(
    values: Array<Color>,
    delay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Color> = animateValuesAsState(
    values = values,
    getValueAtFrame = ValueAtFrame.Color,
    delay = delay,
    animationSpec = animationSpec,
)

@Composable
fun <T> animateValuesAsState(
    vararg values: T,
    getValueAtFrame: ValueAtFrame<T>,
    delay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<T> {
    val animationPairs by rememberUpdatedState(values.toList().zipWithNext())
    val maxValue by remember(animationPairs) { derivedStateOf { animationPairs.size.toFloat() } }
    val currentValue = remember(animationPairs) { mutableStateOf(animationPairs.first().first) }

    LaunchedEffect(keys = animationPairs.toTypedArray()) {
        if (delay > 0) {
            delay(delay)
        }

        val (_, setValue) = currentValue
        animate(
            initialValue = 0f,
            targetValue = maxValue,
            animationSpec = animationSpec,
        ) { frame, _ ->
            when {
                frame >= maxValue -> setValue(animationPairs.last().second)
                frame <= 0f -> setValue(animationPairs.first().first)
                else -> {
                    val integerPart = frame.toInt()
                    val decimalPart = frame - integerPart
                    val (initialValue, finalValue) = animationPairs[frame.toInt()]
                    setValue(
                        getValueAtFrame(decimalPart, initialValue, finalValue)
                    )
                }
            }
        }
    }

    return currentValue
}
