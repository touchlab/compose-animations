package co.touchlab.compose.animations.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.touchlab.compose.animations.page.EASING_ROUTE
import co.touchlab.compose.animations.page.EasingDemo
import co.touchlab.compose.animations.page.HOME_ROUTE
import co.touchlab.compose.animations.page.Home

@Composable
fun RootNav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE,
    ) {
        composable(HOME_ROUTE) { Home(navController) }
        composable(EASING_ROUTE) { EasingDemo(navController) }
    }
}
