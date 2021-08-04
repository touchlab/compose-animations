package co.touchlab.compose.animations.page

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.touchlab.compose.animations.R

val HOME_ROUTE = "demo/home"

private const val TAG = "Home"
@Composable
fun Home(navController: NavController) {
    Log.d(TAG, "Color: ${Color.Red}")
    Log.d(TAG, "Color: ${Color.Green}")
    Log.d(TAG, "Color: ${Color.Blue}")
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compose Animations Demo") },
            )
        },
    ) {
        HomeContent(navController)
    }
}

@Composable
private fun HomeContent(
    navController: NavController,
) {
    LazyColumn {
        items(ALL_DEMOS) { (name, route) ->
            DemoLink(name = name) {
                navController.navigate(route)
            }
        }
    }
}

@Composable
private fun DemoLink(
    name: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Icon(painter = painterResource(id = R.drawable.arrow_forward), null)
    }
}

@Composable
@Preview
fun DemoLinkPreview() {
    DemoLink("Hello World") {}
}

private val ALL_DEMOS = listOf(
    "Easing" to EASING_ROUTE,
    "Value Animator (Compat)" to VALUE_ANIMATOR_COMPAT_ROUTE,
    "Value Animator" to VALUE_ANIMATOR_ROUTE,
)
