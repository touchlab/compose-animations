package co.touchlab.composeanimations.value.animator

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

@Composable
fun animateFloatAsState(
    initialValue: Float,
    targetValue: Float,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
) = animateFloatValuesAsState(
    values = floatArrayOf(initialValue, targetValue),
    startDelay = startDelay,
    animationSpec = animationSpec
)

@Composable
fun animateIntAsState(
    initialValue: Int,
    targetValue: Int,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
) = animateIntValuesAsState(
    values = arrayOf(initialValue, targetValue),
    startDelay = startDelay,
    animationSpec = animationSpec
)

@Composable
fun animateDpAsState(
    initialValue: Dp,
    targetValue: Dp,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
) = animateDpValuesAsState(
    values = arrayOf(initialValue, targetValue),
    startDelay = startDelay,
    animationSpec = animationSpec
)

@Composable
fun animateSizeAsState(
    initialValue: Size,
    targetValue: Size,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
) = animateSizeValuesAsState(
    values = arrayOf(initialValue, targetValue),
    startDelay = startDelay,
    animationSpec = animationSpec
)

@Composable
fun animateOffsetAsState(
    initialValue: Offset,
    targetValue: Offset,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
) = animateOffsetValuesAsState(
    values = arrayOf(initialValue, targetValue),
    startDelay = startDelay,
    animationSpec = animationSpec
)

@Composable
fun animateRectAsState(
    initialValue: Rect,
    targetValue: Rect,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
) = animateRectValuesAsState(
    values = arrayOf(initialValue, targetValue),
    startDelay = startDelay,
    animationSpec = animationSpec
)

@Composable
fun animateIntOffsetAsState(
    initialValue: IntOffset,
    targetValue: IntOffset,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
) = animateIntOffsetValuesAsState(
    values = arrayOf(initialValue, targetValue),
    startDelay = startDelay,
    animationSpec = animationSpec
)

@Composable
fun animateIntSizeAsState(
    initialValue: IntSize,
    targetValue: IntSize,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
) = animateIntSizeValuesAsState(
    values = arrayOf(initialValue, targetValue),
    startDelay = startDelay,
    animationSpec = animationSpec
)

@Composable
fun animateColorAsState(
    initialValue: Color,
    targetValue: Color,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
) = animateColorValuesAsState(
    values = arrayOf(initialValue, targetValue),
    startDelay = startDelay,
    animationSpec = animationSpec
)

@Composable
inline fun <reified T> animateAsState(
    initialValue: T,
    targetValue: T,
    getValueAtFraction: ValueAtFraction<T>,
    startDelay: Long = 0,
    animationSpec: AnimationSpec<Float> = spring(),
): State<T> = animateValuesAsState(
    values = arrayOf(initialValue, targetValue),
    getValueAtFraction = getValueAtFraction,
    startDelay = startDelay,
    animationSpec = animationSpec,
)
