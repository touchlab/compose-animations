package co.touchlab.composeanimations.easing

import androidx.compose.animation.core.Easing
import kotlin.math.pow

// https://easings.net/#easeInQuad
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseInQuad"),
)
object EaseInQuad : Easing {
    override fun transform(fraction: Float): Float =
        fraction.pow(2)
}

// https://easings.net/#easeOutQuad
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseOutQuad"),
)
object EaseOutQuad : Easing {
    override fun transform(fraction: Float): Float =
        1 - (1 - fraction).pow(2)
}

// https://easings.net/#easeInOutQuad
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseInOutQuad"),
)
object EaseInOutQuad : Easing {
    override fun transform(fraction: Float): Float = if (fraction < 0.5f) {
        2 * fraction.pow(2)
    } else {
        1 - (-2 * fraction + 2).pow(2) / 2
    }
}
