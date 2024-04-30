package co.touchlab.composeanimations.animations

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import co.touchlab.composeanimations.animations.screen.HomeScreen
import co.touchlab.composeanimations.animations.theme.ComposeAnimationsTheme

@Composable
fun App() {
    ComposeAnimationsTheme {
        Surface(color = MaterialTheme.colors.background) {
            Navigator(HomeScreen) { navigator ->
                SlideTransition(navigator)
            }
        }
    }
}
