package co.touchlab.compose.animations.page

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.compose.animations.shapes.Ball
import co.touchlab.compose.animations.shapes.Triangle
import co.touchlab.compose.animations.utils.size
import co.touchlab.compose.value.animator.animateColorValuesAsState
import co.touchlab.compose.value.animator.animateFloatValuesAsState
import co.touchlab.compose.value.animator.animateIntValuesAsState
import kotlinx.coroutines.delay

@Composable
fun ValueAnimatorDemo(goBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Value Animator") },
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            ValueAnimatorContent()
        }
    }
}

@Composable
private fun ValueAnimatorContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            SpinningTriangle()
            ColorFadingTriangle()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            BouncingDots()
            LoadingButton()
        }
    }
}

@Composable
private fun BouncingDots() {
    // Based on https://github.com/81813780/AVLoadingIndicatorView/blob/master/library/src/main/java/com/wang/avi/indicators/BallPulseSyncIndicator.java
    val ballsRange = (0..2)
    val ballSize = 12.dp
    val animators = ballsRange.map { index ->
        animateFloatValuesAsState(
            values = floatArrayOf(0f, ballSize.value, 0f),
            startDelay = (1 + index) * 70L,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 200 * ballsRange.size,
                ),
            )
        )
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Float Animator",
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
private fun LoadingButton() {
    var isLoading by remember { mutableStateOf(false) }
    if (isLoading) {
        LaunchedEffect(key1 = isLoading) {
            delay(1500)
            isLoading = false
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Trigger animation",
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
                val ballOffset by animateFloatValuesAsState(
                    values = floatArrayOf(-35f, 35f),
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 175,
                        ),
                        repeatMode = RepeatMode.Reverse,
                    )
                )

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
fun SpinningTriangle() {
    val rotateXAnimation by animateIntValuesAsState(
        values = arrayOf(0, 180, 180, 0, 0),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2500,
                easing = LinearEasing
            ),
        )
    )

    val rotateYAnimation by animateIntValuesAsState(
        values = arrayOf(0, 0, 180, 180, 0),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2500,
                easing = LinearEasing
            ),
        )
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Int animator",
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
fun ColorFadingTriangle() {
    val colorAnimation by animateColorValuesAsState(
        values = arrayOf(Color.Red, Color.Green, Color.Blue),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2500,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Reverse,
        ),
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "ARGB animator",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Triangle(
            modifier = Modifier.padding(top = 8.dp),
            backgroundColor = colorAnimation
        )
    }
}
