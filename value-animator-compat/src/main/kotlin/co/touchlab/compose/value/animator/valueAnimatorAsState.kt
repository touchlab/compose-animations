package co.touchlab.compose.value.animator

import android.animation.ValueAnimator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember

@Composable
inline fun <reified T> ValueAnimator.observeAsState(
    initialValue: T,
): State<T> = produceState(initialValue = initialValue) {
    removeAllUpdateListeners()
    addUpdateListener { value = it.animatedValue as T }
}

@Composable
internal fun <T> valueAnimatorChangesAsState(
    values: Array<T>,
    animator: ValueAnimator,
): State<T> {
    val state = remember(keys = values) { mutableStateOf(values.first()) }

    DisposableEffect(keys = values) {
        val (_, updateCurrentValue) = state
        animator.addUpdateListener { updateCurrentValue(it.animatedValue as T) }
        animator.start()

        onDispose {
            animator.removeAllUpdateListeners()
            animator.cancel()
        }
    }

    return state
}
