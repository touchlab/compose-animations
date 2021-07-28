package co.touchlab.compose.easing

import androidx.compose.animation.core.Easing
import kotlin.math.pow

// https://easings.net/#easeInBounce
object EaseInBounce : Easing {
    override fun transform(fraction: Float): Float =
        1 - EaseOutBounce.transform(1 - fraction)
}

// https://easings.net/#easeOutBounce
object EaseOutBounce : Easing {
    override fun transform(fraction: Float): Float {
        val n1 = 7.5625f
        val d1 = 2.75f

        return when {
            fraction < (1f / d1) -> n1 * fraction.pow(2)
            fraction < (2f / d1) -> {
                val fractionSub = fraction - (1.5f / d1)
                n1 * fractionSub.pow(2) + 0.75f
            }
            fraction < (2.5f / d1) -> {
                val fractionSub = fraction - (2.25f / d1)
                n1 * fractionSub.pow(2) + 0.9375f
            }
            else -> {
                val fractionSub = fraction - (2.625f / d1)
                n1 * fractionSub.pow(2) + 0.984375f
            }
        }
    }
}

// https://easings.net/#easeInOutBounce
object EaseInOutBounce : Easing {
    override fun transform(fraction: Float): Float = if (fraction < 0.5f) {
        (1 - EaseOutBounce.transform(1 - 2 * fraction)) / 2
    } else {
        (1 + EaseOutBounce.transform(2 * fraction - 1)) / 2
    }
}
