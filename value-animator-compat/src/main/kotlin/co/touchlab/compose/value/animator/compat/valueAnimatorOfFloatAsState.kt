package co.touchlab.compose.value.animator.compat

import android.animation.ValueAnimator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember

@Composable
fun rememberFloatValueAnimator(
    vararg values: Float,
    setupAnimator: ValueAnimator.() -> Unit = {},
): ValueAnimator = remember(keys = values.toTypedArray()) {
    ValueAnimator.ofFloat(*values).apply(setupAnimator)
}


@Composable
fun valueAnimatorOfFloatAsState(
    vararg values: Float,
    setupAnimator: ValueAnimator.() -> Unit = {},
): State<Float> {
    val animator = rememberFloatValueAnimator(
        values = values,
        setupAnimator = setupAnimator
    )

    return valueAnimatorChangesAsState(
        values = values.toTypedArray(),
        animator = animator,
    )
}
