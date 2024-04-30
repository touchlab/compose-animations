package co.touchlab.composeanimations.value.animator.utils

import androidx.compose.ui.geometry.Rect

operator fun Rect.minus(other: Rect): Rect = Rect(
    left = this.left - other.left,
    right = this.right - other.right,
    top = this.top - other.top,
    bottom = this.bottom - other.bottom,
)

operator fun Rect.plus(other: Rect): Rect = Rect(
    left = this.left + other.left,
    right = this.right + other.right,
    top = this.top + other.top,
    bottom = this.bottom + other.bottom,
)

operator fun Rect.times(value: Float): Rect = Rect(
    left = this.left * value,
    right = this.right * value,
    top = this.top * value,
    bottom = this.bottom * value,
)

operator fun Rect.div(value: Float): Rect = Rect(
    left = this.left / value,
    right = this.right / value,
    top = this.top / value,
    bottom = this.bottom / value,
)
