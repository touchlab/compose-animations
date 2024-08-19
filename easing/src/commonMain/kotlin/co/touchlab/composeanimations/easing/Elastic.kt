package co.touchlab.composeanimations.easing

import androidx.compose.animation.core.Easing
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sin

// https://easings.net/#easeInElastic
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseInElastic"),
)
object EaseInElastic : Easing {
    override fun transform(fraction: Float): Float {
        val c4: Float = (2 * PI.toFloat()) / 3

        return if (fraction == 0f || fraction == 1f) {
            fraction
        } else {
            -(2f.pow(10 * fraction - 10)) * sin((fraction * 10 - 10.75f) * c4)
        }
    }
}

// https://easings.net/#easeOutElastic
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseOutElastic"),
)
object EaseOutElastic : Easing {
    override fun transform(fraction: Float): Float {
        val c4: Float = (2 * PI.toFloat()) / 3

        return if (fraction == 0f || fraction == 1f) {
            fraction
        } else {
            2f.pow(-10 * fraction) * sin((fraction * 10 - 0.75f) * c4) + 1
        }
    }
}

// https://easings.net/#easeInOutElastic
@Deprecated(
    "Use default implementations from Compose Multiplatform",
    replaceWith = ReplaceWith("androidx.compose.animation.core.EaseInOutElastic"),
)
object EaseInOutElastic : Easing {
    override fun transform(fraction: Float): Float {
        val c5: Float = (2 * PI.toFloat()) / 4.5f

        return when {
            fraction == 0f || fraction == 1f -> fraction
            fraction < 0.5f -> -(2f.pow(20 * fraction - 10) * sin((20 * fraction - 11.125f) * c5)) / 2
            else -> (2f.pow(-20 * fraction + 10) * sin((20 * fraction - 11.125f) * c5)) / 2 + 1
        }
    }
}
