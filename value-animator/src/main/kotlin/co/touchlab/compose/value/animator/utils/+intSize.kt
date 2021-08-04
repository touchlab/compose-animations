package co.touchlab.compose.value.animator.utils

import androidx.compose.ui.unit.IntSize
import kotlin.math.roundToInt

operator fun IntSize.minus(other: IntSize): IntSize = IntSize(
    width = this.width - other.width,
    height = this.height - other.height
)

operator fun IntSize.plus(other: IntSize): IntSize = IntSize(
    width = this.width + other.width,
    height = this.height + other.height
)

operator fun IntSize.times(value: Float): IntSize = IntSize(
    width = (this.width * value).toInt(),
    height = (this.height * value).toInt()
)

operator fun IntSize.div(value: Float): IntSize = IntSize(
    width = (this.width / value).roundToInt(),
    height = (this.height / value).roundToInt()
)
