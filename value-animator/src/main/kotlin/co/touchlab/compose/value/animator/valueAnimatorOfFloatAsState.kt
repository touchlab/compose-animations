package co.touchlab.compose.value.animator

import android.animation.ValueAnimator
import androidx.compose.runtime.*

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
