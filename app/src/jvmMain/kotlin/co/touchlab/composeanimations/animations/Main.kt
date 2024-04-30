package co.touchlab.composeanimations.animations

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() {
    application {
        val windowState = rememberWindowState(
            size = DpSize(393.dp, 852.dp)
        )

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            resizable = false,
            title = "Compose Animations"
        ) {
            App()
        }
    }
}
