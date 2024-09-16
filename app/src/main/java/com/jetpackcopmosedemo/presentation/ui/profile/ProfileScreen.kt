package com.jetpackcopmosedemo.presentation.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.jetpackcopmosedemo.presentation.navigation.Routes
import com.jetpackcopmosedemo.presentation.theme.JetpackComposeDemoTheme
import com.jetpackcopmosedemo.utils.clickableSingle

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfileScreen(
    name: String, navController: NavHostController
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val listState = rememberLazyGridState(initialFirstVisibleItemScrollOffset = 1)
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        state = listState
    ) {
        items(10, key = { index -> index }) { index ->
            Box(
                modifier = Modifier
                    .size(width = screenWidth / 4, height = screenHeight / 4)
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.inversePrimary)
                    .clickableSingle(onClick = {
                        navController.navigate(
                            Routes.Profile.Details.createRoute(index.toString())
                        )
                    }),
            ) {
                Image(
                    painter = rememberImagePainter(data = "https://picsum.photos/${(index + 1) * 200}/300"),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .clip(MaterialTheme.shapes.medium)
                )
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
fun ProfilePreview() {
    JetpackComposeDemoTheme {
        val navController: NavHostController = rememberNavController()
        ProfileScreen("Android", navController = navController)
    }
}