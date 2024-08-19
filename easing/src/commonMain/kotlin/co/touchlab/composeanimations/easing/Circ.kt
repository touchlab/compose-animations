package co.touchlab.composeanimations.easing

import androidx.compose.animation.core.Easing
import kotlin.math.pow
import kotlin.math.sqrt

// https://easings.net/#easeInCirc
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseInCirc"),
)
object EaseInCirc : Easing {
    override fun transform(fraction: Float): Float =
        1 - sqrt(1 - fraction.pow(2))
}

// https://easings.net/#easeOutCirc
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseOutCirc"),
)
object EaseOutCirc : Easing {
    override fun transform(fraction: Float): Float =
        sqrt(1 - (fraction - 1).pow(2))
}

// https://easings.net/#easeInOutCirc
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseInOutCirc"),
)
object EaseInOutCirc : Easing {
    override fun transform(fraction: Float): Float = if (fraction < 0.5f) {
        (1 - sqrt(1 - (2 * fraction).pow(2))) / 2
    } else {
        (sqrt(1 - (-2 * fraction + 2).pow(2)) + 1) / 2
    }
}
