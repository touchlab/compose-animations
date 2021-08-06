package co.touchlab.compose.value.animator.compat

import android.animation.ValueAnimator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember

@Composable
fun ValueAnimator.observeAsState(
    initialValue: Int,
): State<Int> = produceState(initialValue = initialValue) {
    removeAllUpdateListeners()
    addUpdateListener { value = it.animatedValue as Int }
}

@Composable
fun ValueAnimator.observeAsState(
    initialValue: Float,
): State<Float> = produceState(initialValue = initialValue) {
    removeAllUpdateListeners()
    addUpdateListener { value = it.animatedValue as Float }
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
