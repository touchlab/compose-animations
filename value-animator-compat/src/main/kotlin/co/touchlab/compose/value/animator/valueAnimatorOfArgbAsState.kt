package co.touchlab.compose.value.animator

import android.animation.ValueAnimator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember

@Composable
fun rememberArgbValueAnimator(
    vararg values: Int,
    setupAnimator: ValueAnimator.() -> Unit = {},
): ValueAnimator = remember(keys = values.toTypedArray()) {
    ValueAnimator.ofArgb(*values).apply(setupAnimator)
}

@Composable
fun valueAnimatorOfArgbAsState(
    vararg values: Int,
    setupAnimator: ValueAnimator.() -> Unit = {},
): State<Int> {
    val animator = rememberArgbValueAnimator(
        values = values,
        setupAnimator = setupAnimator
    )

    return valueAnimatorChangesAsState(
        values = values.toTypedArray(),
        animator = animator,
    )
}
