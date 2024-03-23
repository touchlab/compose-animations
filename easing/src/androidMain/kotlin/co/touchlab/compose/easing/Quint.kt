package co.touchlab.compose.easing

import androidx.compose.animation.core.Easing
import kotlin.math.pow

// https://easings.net/#easeInQuint
object EaseInQuint : Easing {
    override fun transform(fraction: Float): Float =
        1 - (1 - fraction).pow(5)
}

// https://easings.net/#easeOutQuint
object EaseOutQuint : Easing {
    override fun transform(fraction: Float): Float =
        1 - (1 - fraction).pow(5)
}

// https://easings.net/#easeInOutQuint
object EaseInOutQuint : Easing {
    override fun transform(fraction: Float): Float = if (fraction < 0.5f) {
        16 * fraction.pow(5)
    } else {
        1 - (-2 * fraction + 2).pow(5) / 2
    }
}
