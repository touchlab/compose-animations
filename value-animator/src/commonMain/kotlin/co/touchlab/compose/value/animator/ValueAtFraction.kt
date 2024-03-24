package co.touchlab.compose.value.animator

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
fun interface ValueAtFraction<T> {
    companion object {
        val Float: ValueAtFraction<Float> get() = floatAtFraction
        val Int: ValueAtFraction<Int> get() = intAtFraction
        val Dp: ValueAtFraction<Dp> get() = dpAtFraction
        val Size: ValueAtFraction<Size> get() = sizeAtFraction
        val Offset: ValueAtFraction<Offset> get() = offsetAtFraction
        val Rect: ValueAtFraction<Rect> get() = rectAtFraction
        val IntOffset: ValueAtFraction<IntOffset> get() = intOffsetAtFraction
        val IntSize: ValueAtFraction<IntSize> get() = intSizeAtFraction
        val Color: ValueAtFraction<Color> get() = colorAtFraction
    }

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

private val floatAtFraction = ValueAtFraction<Float> { fraction, initialValue, finalValue ->
    initialValue + (finalValue - initialValue) * fraction
}

private val intAtFraction = ValueAtFraction<Int> { fraction, initialValue, finalValue ->
    initialValue + ((finalValue - initialValue) * fraction).toInt()
}

private val dpAtFraction = ValueAtFraction<Dp> { fraction, initialValue, finalValue ->
    initialValue + (finalValue - initialValue) * fraction
}

private val sizeAtFraction = ValueAtFraction<Size> { fraction, initialValue, finalValue ->
    initialValue + (finalValue - initialValue) * fraction
}

private val offsetAtFraction = ValueAtFraction<Offset> { fraction, initialValue, finalValue ->
    initialValue + (finalValue - initialValue) * fraction
}

private val rectAtFraction = ValueAtFraction<Rect> { fraction, initialValue, finalValue ->
    initialValue + (finalValue - initialValue) * fraction
}

private val intOffsetAtFraction = ValueAtFraction<IntOffset> { fraction, initialValue, finalValue ->
    initialValue + (finalValue - initialValue) * fraction
}

private val intSizeAtFraction = ValueAtFraction<IntSize> { fraction, initialValue, finalValue ->
    initialValue + (finalValue - initialValue) * fraction
}

private val colorAtFraction = ValueAtFraction<Color> { fraction, initialValue, finalValue ->
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
