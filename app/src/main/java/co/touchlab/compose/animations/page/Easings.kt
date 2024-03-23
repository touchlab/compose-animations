package co.touchlab.compose.animations.page

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.touchlab.compose.easing.EaseInBack
import co.touchlab.compose.easing.EaseInBounce
import co.touchlab.compose.easing.EaseInCirc
import co.touchlab.compose.easing.EaseInCubic
import co.touchlab.compose.easing.EaseInElastic
import co.touchlab.compose.easing.EaseInExpo
import co.touchlab.compose.easing.EaseInOutBack
import co.touchlab.compose.easing.EaseInOutBounce
import co.touchlab.compose.easing.EaseInOutCirc
import co.touchlab.compose.easing.EaseInOutCubic
import co.touchlab.compose.easing.EaseInOutElastic
import co.touchlab.compose.easing.EaseInOutExpo
import co.touchlab.compose.easing.EaseInOutQuad
import co.touchlab.compose.easing.EaseInOutQuart
import co.touchlab.compose.easing.EaseInOutQuint
import co.touchlab.compose.easing.EaseInOutSine
import co.touchlab.compose.easing.EaseInQuad
import co.touchlab.compose.easing.EaseInQuart
import co.touchlab.compose.easing.EaseInQuint
import co.touchlab.compose.easing.EaseInSine
import co.touchlab.compose.easing.EaseOutBack
import co.touchlab.compose.easing.EaseOutBounce
import co.touchlab.compose.easing.EaseOutCirc
import co.touchlab.compose.easing.EaseOutCubic
import co.touchlab.compose.easing.EaseOutElastic
import co.touchlab.compose.easing.EaseOutExpo
import co.touchlab.compose.easing.EaseOutQuad
import co.touchlab.compose.easing.EaseOutQuart
import co.touchlab.compose.easing.EaseOutQuint
import co.touchlab.compose.easing.EaseOutSine

val EASING_ROUTE = "demo/easings"

@Composable
fun EasingDemo(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Easing functions") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            EasingDemoContent()
        }
    }
}

@Composable
private fun EasingDemoContent() {
    LazyColumn {
        items(ALL_EASIGNS.toList()) { (name, easing) ->
            Graph(
                name = name,
                easingFunction = easing
            )
        }
    }
}

@Composable
private fun Graph(
    name: String,
    easingFunction: Easing,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2500,
                easing = LinearEasing,
            )
        )
    )
    val value by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2500,
                easing = easingFunction,
            )
        )
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(vertical = 24.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawLine(
                color = Color.Blue,
                strokeWidth = 3f,
                start = Offset.Zero,
                end = Offset(0f, canvasHeight),
            )

            drawLine(
                color = Color.Blue,
                strokeWidth = 3f,
                start = Offset(canvasWidth, 0f),
                end = Offset(canvasWidth, canvasHeight),
            )

            drawLine(
                color = Color.Red,
                strokeWidth = 3f,
                start = Offset(0f, canvasHeight),
                end = Offset(canvasWidth, canvasHeight),
            )

            drawLine(
                color = Color.Red,
                strokeWidth = 3f,
                start = Offset(0f, 0f),
                end = Offset(canvasWidth, 0f),
            )

            val canvasSizeInt = canvasWidth.toInt()
            for (i in 1..canvasSizeInt) {
                val currentTime = (i + 1) / canvasSizeInt.toFloat()
                val previousTime = (i - 1) / canvasSizeInt.toFloat()

                drawLine(
                    color = Color.LightGray,
                    strokeWidth = 2f,
                    start = Offset(
                        x = canvasWidth * previousTime,
                        y = canvasHeight * (1 - easingFunction.transform(previousTime))
                    ),
                    end = Offset(
                        x = canvasWidth * currentTime,
                        y = canvasHeight * (1 - easingFunction.transform(currentTime))
                    ),
                )
            }

            drawCircle(
                color = Color.Black,
                radius = 10f,
                center = Offset(canvasWidth * time, canvasHeight * (1 - value))
            )
        }
    }
}

@Composable
@Preview
fun GraphPreview() {
    Graph(
        name = "Some function",
        easingFunction = LinearEasing
    )
}

private val ALL_EASIGNS = mapOf(
    "LinearEasing (Compose)" to LinearEasing,
    "FastOutSlowInEasing (Compose)" to FastOutSlowInEasing,
    "LinearOutSlowInEasing (Compose)" to LinearOutSlowInEasing,
    "FastOutLinearInEasing (Compose)" to FastOutLinearInEasing,
    "EaseInSine" to EaseInSine,
    "EaseOutSine" to EaseOutSine,
    "EaseInOutSine" to EaseInOutSine,
    "EaseInQuad" to EaseInQuad,
    "EaseOutQuad" to EaseOutQuad,
    "EaseInOutQuad" to EaseInOutQuad,
    "EaseInCubic" to EaseInCubic,
    "EaseOutCubic" to EaseOutCubic,
    "EaseInOutCubic" to EaseInOutCubic,
    "EaseInQuart" to EaseInQuart,
    "EaseOutQuart" to EaseOutQuart,
    "EaseInOutQuart" to EaseInOutQuart,
    "EaseInQuint" to EaseInQuint,
    "EaseOutQuint" to EaseOutQuint,
    "EaseInOutQuint" to EaseInOutQuint,
    "EaseInExpo" to EaseInExpo,
    "EaseOutExpo" to EaseOutExpo,
    "EaseInOutExpo" to EaseInOutExpo,
    "EaseInCirc" to EaseInCirc,
    "EaseOutCirc" to EaseOutCirc,
    "EaseInOutCirc" to EaseInOutCirc,
    "EaseInBack" to EaseInBack,
    "EaseOutBack" to EaseOutBack,
    "EaseInOutBack" to EaseInOutBack,
    "EaseInElastic" to EaseInElastic,
    "EaseOutElastic" to EaseOutElastic,
    "EaseInOutElastic" to EaseInOutElastic,
    "EaseInBounce" to EaseInBounce,
    "EaseOutBounce" to EaseOutBounce,
    "EaseInOutBounce" to EaseInOutBounce,
)
