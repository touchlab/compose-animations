# Value Animator Compat

This library provides complete access to the
[ValueAnimator](https://developer.android.com/reference/android/animation/ValueAnimator) APIs in Compose.

The main goal is to reduce friction while moving more complex animations from the previous API to Compose.

## API

### **valueAnimatorOf{Type}AsState**

Creates an instance of `ValueAnimator` for the given type and returns a State<{Type}>. This value
animator will use the values passed as argument when creating a new instance, and configure it using
the block parameter.

This group of functions are "fire-and-forget" animations, handling with start/stop/listening
automatically. All updates will be posted to the returning state.

The initial value will be the first value passed as argument to the ValueAnimator.

##### **valueAnimatorOfIntAsState**

Creates an instance of `ValueAnimator.ofInt` with the given configuration, and returns an
`State<Int>`.

```kotlin
val currentState: State<Int> = valueAnimatorOfIntAsState(0, 4, 0, 8, 0) {
    this.interpolator = LinearInterpolator()
    this.duration = 250
    this.repeatMode = ValueAnimator.REVERSE
    this.repeatCount = ValueAnimator.INFINITE
}
```

##### **valueAnimatorOfFloatAsState**f

Creates an instance of `ValueAnimator.ofFloat` with the given configuration, and returns an
`State<Float>`.

```kotlin
val currentState: State<Float> = valueAnimatorOfFloatAsState(0f, 0.5f, 0f, 2.75f, 0f) {
    this.interpolator = LinearInterpolator()
    this.duration = 250
    this.repeatMode = ValueAnimator.REVERSE
    this.repeatCount = ValueAnimator.INFINITE
}
```

##### **valueAnimatorOfArgbAsState**

Creates an instance of `ValueAnimator.ofArgb` with the given configuration, and returns an
`State<Int>`. The value from the state represents a color.

```kotlin
val currentState: State<Int> = valueAnimatorOfArgbAsState(Color.RED, Color.BLUE) {
    this.interpolator = LinearInterpolator()
    this.duration = 250
    this.repeatMode = ValueAnimator.REVERSE
    this.repeatCount = ValueAnimator.INFINITE
}
```

### **remember{Type}ValueAnimator**

Creates an instance of `ValueAnimator` for the given type and returns it. Using this group of
functions, you will be responsible to start, stop and listen this instance.

This type of ValueAnimator can be useful if you plan to use an AnimatorSet to control the animations.
For more details about how to use AnimatorSet, check the `rememberAnimatorSet()` section.

When you use this group of functions, you can use the `ValueAnimator.observeAsState` to listen 
the value changes.

##### **rememberIntValueAnimator**

Creates an instance of `ValueAnimator.ofInt` with the given configuration and returns it.

```kotlin
val valueAnimator = rememberIntValueAnimator(0, 4, 0, 8, 0) {
    this.interpolator = LinearInterpolator()
    this.duration = 250
    this.repeatMode = ValueAnimator.REVERSE
    this.repeatCount = ValueAnimator.INFINITE
}
```

##### **rememberFloatValueAnimator**

Creates an instance of `ValueAnimator.ofFloat` with the given configuration and returns it.

```kotlin
val valueAnimator = rememberFloatValueAnimator(0, 4, 0, 8, 0) {
    this.interpolator = LinearInterpolator()
    this.duration = 250
    this.repeatMode = ValueAnimator.REVERSE
    this.repeatCount = ValueAnimator.INFINITE
}
```

##### **rememberIntValueAnimator**

Creates an instance of `ValueAnimator.ofArgb` with the given configuration and returns it.

```kotlin
val valueAnimator = rememberArgbValueAnimator(Color.RED, Color.BLUE) {
    this.interpolator = LinearArgberpolator()
    this.duration = 250
    this.repeatMode = ValueAnimator.REVERSE
    this.repeatCount = ValueAnimator.INFINITE
}
```

### **observeAsState**

Converts a ValueAnimator instance to an State<{Type}> instance. The supported types are `Int` and 
`Float`.

```kotlin
val rotationXAnimator = rememberIntValueAnimator(0, 180, 270, 315, 360)
val rotationX by rotationXAnimator.observeAsState(0)

val alphaAnimator = rememberFloatValueAnimator(1f, 0.5f, 0.25f, 0.125f, 0f)
val alpha by rotationXAnimator.observeAsState(1f)
```

### **rememberAnimatorSet**

Creates an instance of `ComposableAnimatorSet` and returns it. With this instance, you can start/stop
all your animations at same time.


```kotlin
val rotationXAnimator = rememberFloatValueAnimator(0f, 180f, 270f, 315f, 360f)
val rotationX by rotationXAnimator.observeAsState(0f)

val colorAnimator = rememberArgbValueAnimator(Color.RED, Color.BLUE)
val color by rotationXAnimator.observeAsState(Color.RED)

val animatorSet = rememberAnimatorSet {
    play(rotationXAnimator).before(colorAnimator)
    duration = 1500
}

Column {
    Triangle(
        modifier = Modifier
            .padding(top = 8.dp)
            .graphicsLayer(rotationX = rotationX),
        backgroundColor = androidx.compose.ui.graphics.Color(color)
    )
    Button(
        modifier = Modifier.padding(top = 8.dp),
        onClick = { animatorSet.toggle() }, // Start and stop the animation when clicking
    ) {
        Text(text = if (animatorSet.isAnimating) "Stop" else "Play")
    }
}
```
