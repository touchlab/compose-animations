package co.touchlab.composeanimations.easing

import androidx.compose.animation.core.Easing
import kotlin.math.pow

// https://easings.net/#easeInBack
object EaseInBack : Easing {
    override fun transform(fraction: Float): Float {
        val c1 = 1.70158f
        val c3 = c1 + 1

        return c3 * fraction.pow(3) - c1 * fraction.pow(2)
    }
}

// https://easings.net/#easeOutBack
object EaseOutBack : Easing {
    override fun transform(fraction: Float): Float {
        val c1 = 1.70158f
        val c3 = c1 + 1

        return 1 + c3 * (fraction - 1).pow(3) + c1 * (fraction - 1).pow(2)
    }
}

// https://easings.net/#easeInOutBack
object EaseInOutBack : Easing {
    override fun transform(fraction: Float): Float {
        val c1 = 1.70158f
        val c2 = c1 * 1.525f

        return if (fraction < 0.5f) {
            ((2 * fraction).pow(2) * ((c2 + 1) * 2 * fraction - c2)) / 2
        } else {
            ((2 * fraction - 2).pow(2) * ((c2 + 1) * (fraction * 2 - 2) + c2) + 2) / 2
        }
    }
}
