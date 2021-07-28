package co.touchlab.compose.easing

import androidx.compose.animation.core.Easing
import kotlin.math.pow

// https://easings.net/#easeInQuart
object EaseInQuart : Easing {
    override fun transform(fraction: Float): Float =
        fraction.pow(4)
}

// https://easings.net/#easeOutQuart
object EaseOutQuart : Easing {
    override fun transform(fraction: Float): Float =
        1 - (1 - fraction).pow(4)
}

// https://easings.net/#easeInOutQuart
object EaseInOutQuart : Easing {
    override fun transform(fraction: Float): Float = if (fraction < 0.5f) {
        8 * fraction.pow(4)
    } else {
        1 - (-2 * fraction + 2).pow(4) / 2
    }
}
