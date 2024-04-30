package co.touchlab.composeanimations.animations.screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.composeanimations.animations.page.ValueAnimatorDemo

object ValueAnimatorScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        ValueAnimatorDemo(goBack = { navigator.pop() })
    }
}
