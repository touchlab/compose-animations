package co.touchlab.compose.animations.screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.compose.animations.page.Home

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Home(
            openEasingDemo = { navigator.push(EasingsScreen) },
            openValueAnimatorDemo = { navigator.push(ValueAnimatorScreen) },
        )
    }
}
