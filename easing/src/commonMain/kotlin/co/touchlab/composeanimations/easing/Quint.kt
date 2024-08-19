package co.touchlab.composeanimations.easing

import androidx.compose.animation.core.Easing
import kotlin.math.pow

// https://easings.net/#easeInQuint
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseInQuint"),
)
object EaseInQuint : Easing {
    override fun transform(fraction: Float): Float =
        1 - (1 - fraction).pow(5)
}

// https://easings.net/#easeOutQuint
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseOutQuint"),
)
object EaseOutQuint : Easing {
    override fun transform(fraction: Float): Float =
        1 - (1 - fraction).pow(5)
}

// https://easings.net/#easeInOutQuint
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseInOutQuint"),
)
object EaseInOutQuint : Easing {
    override fun transform(fraction: Float): Float = if (fraction < 0.5f) {
        16 * fraction.pow(5)
    } else {
        1 - (-2 * fraction + 2).pow(5) / 2
    }
}
