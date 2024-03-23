package co.touchlab.compose.value.animator

internal data class MultipleValuesAnimator<T>(
    val values: List<T>,
) {
    private val groups = values.zipWithNext()

    val initialAnimationValue: T = values.first()
    val targetAnimationValue: T = values.last()

    val initialFrameValue: Float = 0f
    val targetFrameValue: Float = groups.size.toFloat()

    fun animate(frame: Float, valueAtFraction: ValueAtFraction<T>): T = when {
        groups.isEmpty() -> initialAnimationValue
        frame <= initialFrameValue -> initialAnimationValue
        frame >= targetFrameValue -> targetAnimationValue
        else -> {
            val integerPart = frame.toInt()
            val decimalPart = frame - integerPart
            val (initialValue, finalValue) = groups[frame.toInt()]
            valueAtFraction(decimalPart, initialValue, finalValue)
        }
    }
}
