package co.touchlab.compose.easing

import androidx.compose.animation.core.Easing
import kotlin.math.pow

// https://easings.net/#easeInCubic
object EaseInCubic : Easing {
    override fun transform(fraction: Float): Float =
        fraction.pow(3)
}

// https://easings.net/#easeOutCubic
object EaseOutCubic : Easing {
    override fun transform(fraction: Float): Float =
        1 - (1 - fraction).pow(3)
}

// https://easings.net/#easeInOutCubic
object EaseInOutCubic : Easing {
    override fun transform(fraction: Float): Float = if (fraction < 0.5f) {
        4 * fraction.pow(3)
    } else {
        1 - (-2 * fraction + 2).pow(3) / 2
    }
}
