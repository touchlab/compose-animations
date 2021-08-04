package co.touchlab.compose.value.animator

import android.graphics.ColorSpace
import androidx.compose.animation.animateColorAsState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import co.touchlab.compose.value.animator.utils.minus
import co.touchlab.compose.value.animator.utils.plus
import co.touchlab.compose.value.animator.utils.times
import kotlin.math.pow

/**
 * This interface is responsible for converting the fraction value into one value of type T
 * between initialValue and finalValue
 */
fun interface ValueAtFrame<T> {
    companion object {}

    /**
     * @param fraction Float representing an animation progress. Always between 0f and 1f
     * @param initialValue The initial value from the range
     * @param finalValue The final value from the range
     */
    operator fun invoke(
        fraction: Float,
        initialValue: T,
        finalValue: T,
    ): T
}

private val FloatAtFrame = ValueAtFrame<Float> { fraction, initialValue, finalValue ->
    initialValue + (finalValue - initialValue) * fraction
}

val ValueAtFrame.Companion.Float: ValueAtFrame<Float>
    get() = FloatAtFrame

private val IntAtFrame = ValueAtFrame<Int> { fraction, initialValue, finalValue ->
    initialValue + ((finalValue - initialValue) * fraction).toInt()
}

val ValueAtFrame.Companion.Int: ValueAtFrame<Int>
    get() = IntAtFrame

private val DpAtFrame = ValueAtFrame<Dp> { fraction, initialValue, finalValue ->
    initialValue + (finalValue - initialValue) * fraction
}

val ValueAtFrame.Companion.Dp: ValueAtFrame<Dp>
    get() = DpAtFrame

private val SizeAtFrame = ValueAtFrame<Size> { fraction, initialValue, finalValue ->
    initialValue + (finalValue - initialValue) * fraction
}

val ValueAtFrame.Companion.Size: ValueAtFrame<Size>
    get() = SizeAtFrame

private val OffsetAtFrame = ValueAtFrame<Offset> { fraction, initialValue, finalValue ->
    initialValue + (finalValue - initialValue) * fraction
}

val ValueAtFrame.Companion.Offset: ValueAtFrame<Offset>
    get() = OffsetAtFrame

private val RectAtFrame = ValueAtFrame<Rect> { fraction, initialValue, finalValue ->
    initialValue + (finalValue - initialValue) * fraction
}

val ValueAtFrame.Companion.Rect: ValueAtFrame<Rect>
    get() = RectAtFrame

private val IntOffsetAtFrame = ValueAtFrame<IntOffset> { fraction, initialValue, finalValue ->
    initialValue + (finalValue - initialValue) * fraction
}

val ValueAtFrame.Companion.IntOffset: ValueAtFrame<IntOffset>
    get() = IntOffsetAtFrame

private val IntSizeAtFrame = ValueAtFrame<IntSize> { fraction, initialValue, finalValue ->
    initialValue + (finalValue - initialValue) * fraction
}

val ValueAtFrame.Companion.IntSize: ValueAtFrame<IntSize>
    get() = IntSizeAtFrame

private val ColorAtFrame = ValueAtFrame<Color> { fraction, initialValue, finalValue ->
    val initialValueAsSRGB = initialValue.convert(ColorSpaces.Srgb)
    val finalValueAsSRGB = finalValue.convert(ColorSpaces.Srgb)

    val startA = initialValueAsSRGB.alpha
    val startR = initialValueAsSRGB.red.pow(2.2f)
    val startG = initialValueAsSRGB.green.pow(2.2f)
    val startB = initialValueAsSRGB.blue.pow(2.2f)

    val endA = finalValueAsSRGB.alpha
    val endR = finalValueAsSRGB.red.pow(2.2f)
    val endG = finalValueAsSRGB.green.pow(2.2f)
    val endB = finalValueAsSRGB.blue.pow(2.2f)

    Color(
        red = startR + fraction * (endR - startR),
        green = startG + fraction * (endG - startG),
        blue = startB + fraction * (endB - startB),
        alpha = startA + fraction * (endA - startA),
        colorSpace = ColorSpaces.Srgb
    )
}

val ValueAtFrame.Companion.Color: ValueAtFrame<Color>
    get() = ColorAtFrame
