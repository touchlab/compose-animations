package co.touchlab.composeanimations.easing

import androidx.compose.animation.core.Easing
import kotlin.math.pow

// https://easings.net/#easeInQuart
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseInQuart"),
)
object EaseInQuart : Easing {
    override fun transform(fraction: Float): Float =
        fraction.pow(4)
}

// https://easings.net/#easeOutQuart
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseOutQuart"),
)
object EaseOutQuart : Easing {
    override fun transform(fraction: Float): Float =
        1 - (1 - fraction).pow(4)
}

// https://easings.net/#easeInOutQuart
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseInOutQuart"),
)
object EaseInOutQuart : Easing {
    override fun transform(fraction: Float): Float = if (fraction < 0.5f) {
        8 * fraction.pow(4)
    } else {
        1 - (-2 * fraction + 2).pow(4) / 2
    }
}
