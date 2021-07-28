package co.touchlab.compose.easing

import androidx.compose.animation.core.Easing
import kotlin.math.pow

// https://easings.net/#easeInExpo
object EaseInExpo : Easing {
    override fun transform(fraction: Float): Float =
        if (fraction == 0f) fraction else 2f.pow(10 * fraction - 10)
}

// https://easings.net/#easeOutExpo
object EaseOutExpo : Easing {
    override fun transform(fraction: Float): Float =
        if (fraction == 1f) fraction else (1 - 2f.pow(-10 * fraction))
}

// https://easings.net/#easeInOutExpo
object EaseInOutExpo : Easing {
    override fun transform(fraction: Float): Float = when {
        fraction == 0f || fraction == 1f -> fraction
        fraction < 0.5f -> 2f.pow(20 * fraction - 10) / 2
        else -> (2 - 2f.pow(-20 * fraction + 10)) / 2
    }
}
