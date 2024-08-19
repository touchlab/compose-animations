package co.touchlab.composeanimations.easing

import androidx.compose.animation.core.Easing
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

// https://easings.net/#easeInSine
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseInSine"),
)
object EaseInSine : Easing {
    override fun transform(fraction: Float): Float =
        1 - cos((fraction * PI.toFloat()) / 2)
}

// https://easings.net/#easeOutSine
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseOutSine"),
)
object EaseOutSine : Easing {
    override fun transform(fraction: Float): Float =
        sin((fraction * PI.toFloat()) / 2)
}

// https://easings.net/#easeInOutSine
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseInOutSine"),
)
object EaseInOutSine : Easing {
    override fun transform(fraction: Float): Float =
        -(cos(fraction * PI.toFloat()) - 1) / 2
}
