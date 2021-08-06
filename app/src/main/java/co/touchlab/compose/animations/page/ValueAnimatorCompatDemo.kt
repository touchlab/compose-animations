package co.touchlab.compose.animations.page

import android.animation.ValueAnimator
import android.graphics.Color
import android.view.animation.LinearInterpolator
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.touchlab.compose.animations.shapes.Ball
import co.touchlab.compose.animations.shapes.Triangle
import co.touchlab.compose.animations.utils.size
import co.touchlab.compose.value.animator.compat.observeAsState
import co.touchlab.compose.value.animator.compat.rememberAnimatorSet
import co.touchlab.compose.value.animator.compat.rememberArgbValueAnimator
import co.touchlab.compose.value.animator.compat.rememberIntValueAnimator
import co.touchlab.compose.value.animator.compat.valueAnimatorOfArgbAsState
import co.touchlab.compose.value.animator.compat.valueAnimatorOfFloatAsState
import co.touchlab.compose.value.animator.compat.valueAnimatorOfIntAsState
import kotlinx.coroutines.delay

val VALUE_ANIMATOR_COMPAT_ROUTE = "demo/value-animator-compat"

@Composable
fun ValueAnimatorCompatDemo(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Value Animator (Compat)") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        },
    ) { ValueAnimatorCompatContent() }
}

@Composable
private fun ValueAnimatorCompatContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            BouncingDotsCompat()
            LoadingButtonCompat()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            SpinningTriangleCompat()
            ColorFadingTriangleCompat()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            SpinningTriangleAnimatorSetCompat()
        }
    }
}

@Composable
fun BouncingDotsCompat() {
    // Based on https://github.com/81813780/AVLoadingIndicatorView/blob/master/library/src/main/java/com/wang/avi/indicators/BallPulseSyncIndicator.java

    val ballsRange = (0..2)
    val ballSize = 12.dp
    val animators = ballsRange.map { index ->
        valueAnimatorOfFloatAsState(0f, ballSize.value, 0f) {
            duration = 200 * ballsRange.size.toLong()
            repeatCount = ValueAnimator.INFINITE
            startDelay = (1 + index) * 70L
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Float Animator (Compat)",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .width(ballSize * (ballsRange.size + 1)),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            ballsRange.forEach { index ->
                Ball(
                    modifier = Modifier
                        .offset(y = animators[index].value.dp)
                )
            }
        }
    }
}

@Composable
fun LoadingButtonCompat() {
    var isLoading by remember { mutableStateOf(false) }
    if (isLoading) {
        LaunchedEffect(key1 = isLoading) {
            delay(1500)
            isLoading = false
        }
    }
    
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Trigger animation (Compat)",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            modifier = Modifier
                .width(150.dp)
                .padding(top = 4.dp),
            onClick = { if (!isLoading) isLoading = true },
        ) {
            if (isLoading) {
                val ballOffset by valueAnimatorOfFloatAsState(-35f, 35f) {
                    repeatMode = ValueAnimator.REVERSE
                    repeatCount = ValueAnimator.INFINITE
                    duration = 175
                }

                Ball(
                    modifier = Modifier.offset(x = ballOffset.dp)
                )
            } else {
                Text(text = "Login")
            }
        }
    }
}

@Composable
fun SpinningTriangleCompat() {
    val rotateXAnimation by valueAnimatorOfIntAsState(0, 180, 180, 0, 0) {
        interpolator = LinearInterpolator()
        repeatCount = ValueAnimator.INFINITE
        duration = 2500
    }

    val rotateYAnimation by valueAnimatorOfIntAsState(0, 0, 180, 180, 0) {
        interpolator = LinearInterpolator()
        repeatCount = ValueAnimator.INFINITE
        duration = 2500
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Int animator (Compat)",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Triangle(
            modifier = Modifier
                .padding(top = 8.dp)
                .graphicsLayer(
                    rotationX = rotateXAnimation.toFloat(),
                    rotationY = rotateYAnimation.toFloat(),
                )
        )
    }
}

@Composable
fun ColorFadingTriangleCompat() {
    val colorAnimation by valueAnimatorOfArgbAsState(Color.RED, Color.GREEN, Color.BLUE) {
        interpolator = LinearInterpolator()
        repeatCount = ValueAnimator.INFINITE
        repeatMode = ValueAnimator.REVERSE
        duration = 2500
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "ARGB animator (Compat)",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Triangle(
            modifier = Modifier.padding(top = 8.dp),
            backgroundColor = androidx.compose.ui.graphics.Color(colorAnimation)
        )
    }
}

@Composable
fun SpinningTriangleAnimatorSetCompat() {
    val colorAnimator = rememberArgbValueAnimator(Color.RED, Color.GREEN, Color.BLUE, Color.GREEN, Color.RED) {
        repeatCount = ValueAnimator.INFINITE
    }
    val rotationXAnimator = rememberIntValueAnimator(0, 180, 180, 0, 0) {
        repeatCount = ValueAnimator.INFINITE
    }
    val rotationYAnimator = rememberIntValueAnimator(0, 0, 180, 180, 0) {
        repeatCount = ValueAnimator.INFINITE
    }

    val animatorSet = rememberAnimatorSet {
        play(rotationXAnimator)
            .before(rotationYAnimator)
            .before(colorAnimator)
        duration = 1500
    }

    val rotationX by rotationXAnimator.observeAsState(0)
    val rotationY by rotationYAnimator.observeAsState(0)
    val color by colorAnimator.observeAsState(Color.RED)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Animator Set (Compat)",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Triangle(
            modifier = Modifier
                .padding(top = 8.dp)
                .graphicsLayer(

                    rotationX = rotationX.toFloat(),
                    rotationY = rotationY.toFloat(),
                ),
            backgroundColor = androidx.compose.ui.graphics.Color(color)
        )
        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = { animatorSet.toggle() },
        ) {
            Text(text = if (animatorSet.isAnimating) "Stop" else "Play")
        }
    }
}
