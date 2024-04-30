package co.touchlab.composeanimations.easing

import androidx.compose.animation.core.Easing
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

// https://easings.net/#easeInSine
object EaseInSine : Easing {
    override fun transform(fraction: Float): Float =
        1 - cos((fraction * PI.toFloat()) / 2)
}

// https://easings.net/#easeOutSine
object EaseOutSine : Easing {
    override fun transform(fraction: Float): Float =
        sin((fraction * PI.toFloat()) / 2)
}

// https://easings.net/#easeInOutSine
object EaseInOutSine : Easing {
    override fun transform(fraction: Float): Float =
        -(cos(fraction * PI.toFloat()) - 1) / 2
}
