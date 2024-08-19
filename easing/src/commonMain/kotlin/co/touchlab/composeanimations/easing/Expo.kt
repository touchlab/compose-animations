package co.touchlab.composeanimations.easing

import androidx.compose.animation.core.Easing
import kotlin.math.pow

// https://easings.net/#easeInExpo
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseInExpo"),
)
object EaseInExpo : Easing {
    override fun transform(fraction: Float): Float =
        if (fraction == 0f) fraction else 2f.pow(10 * fraction - 10)
}

// https://easings.net/#easeOutExpo
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseOutExpo"),
)
object EaseOutExpo : Easing {
    override fun transform(fraction: Float): Float =
        if (fraction == 1f) fraction else (1 - 2f.pow(-10 * fraction))
}

// https://easings.net/#easeInOutExpo
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseInOutExpo"),
)
object EaseInOutExpo : Easing {
    override fun transform(fraction: Float): Float = when {
        fraction == 0f || fraction == 1f -> fraction
        fraction < 0.5f -> 2f.pow(20 * fraction - 10) / 2
        else -> (2 - 2f.pow(-20 * fraction + 10)) / 2
    }
}
