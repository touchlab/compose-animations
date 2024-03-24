package co.touchlab.compose.animations.page

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Home(
    openEasingDemo: () -> Unit,
    openValueAnimatorDemo: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compose Animations Demo") },
            )
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            HomeContent(
                openEasingDemo = openEasingDemo,
                openValueAnimatorDemo = openValueAnimatorDemo,
            )
        }
    }
}

@Composable
private fun HomeContent(
    openEasingDemo: () -> Unit,
    openValueAnimatorDemo: () -> Unit,
) {
    val allDemos = listOf(
        "Easing" to openEasingDemo,
        "Value Animator" to openValueAnimatorDemo,
    )

    LazyColumn {
        items(allDemos) { (name, callback) ->
            DemoLink(name = name, onClick = callback)
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
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
    }
}

@Composable
@Preview
fun DemoLinkPreview() {
    DemoLink("Hello World") {}
}
