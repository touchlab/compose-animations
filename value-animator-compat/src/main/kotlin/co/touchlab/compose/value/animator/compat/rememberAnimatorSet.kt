package co.touchlab.compose.value.animator.compat

import android.animation.Animator
import android.animation.AnimatorSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class ComposableAnimatorSet(
    val animatorSet: AnimatorSet,
    val isAnimating: Boolean,
    val play: () -> Unit,
    val stop: () -> Unit,
    val toggle: () -> Unit,
)

@Composable
fun rememberAnimatorSet(
    vararg keys: Any?,
    setupAnimator: AnimatorSet.() -> Unit,
): ComposableAnimatorSet {
    val animatorSet = if (keys.isEmpty()) {
        remember { AnimatorSet().apply(setupAnimator) }
    } else {
        remember(keys = keys) { AnimatorSet().apply(setupAnimator) }
    }

    var playing by remember { mutableStateOf(false) }
    DisposableEffect(key1 = playing) {
        animatorSet.removeAllListeners()
        animatorSet.addOnAnimationStop { if (playing) playing = false }

        if (playing) {
            animatorSet.start()
        }

        onDispose { animatorSet.cancel() }
    }

    return remember(key1 = animatorSet, key2 = playing) {
        ComposableAnimatorSet(
            animatorSet = animatorSet,
            isAnimating = playing,
            play = { if (!playing) playing = true },
            stop = { if (playing) playing = false },
            toggle = { playing = !playing }
        )
    }
}

private fun AnimatorSet.addOnAnimationStop(block: () -> Unit) {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {}
        override fun onAnimationRepeat(animation: Animator?) {}

        override fun onAnimationEnd(animation: Animator?) {
            block()
        }

        override fun onAnimationCancel(animation: Animator?) {
            block()
        }
    })
}

