package com.jetpackcopmosedemo.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jetpackcopmosedemo.presentation.theme.JetpackComposeDemoTheme

@Composable
fun HomeScreen(
    name: String,
    navController: NavHostController
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(10, key = { index -> index }) { index ->
            Box(
                modifier = Modifier
                    .size(width = screenWidth / 4, height = screenHeight / 4)
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.inversePrimary)
            ) {
                Text(
                    text = "Hello $name! $index",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreviewPreview() {
    JetpackComposeDemoTheme {
        val navController: NavHostController = rememberNavController()
        HomeScreen("Android", navController)
    }
}