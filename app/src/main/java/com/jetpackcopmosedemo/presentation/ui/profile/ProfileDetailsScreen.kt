package com.jetpackcopmosedemo.presentation.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
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
import com.jetpackcopmosedemo.presentation.navigation.Routes
import com.jetpackcopmosedemo.presentation.theme.JetpackComposeDemoTheme

@Composable
fun ProfileDetailsScreen(
    name: String,
    navController: NavHostController
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Box(
        modifier = Modifier
            .size(width = screenWidth / 4, height = screenHeight / 4)
            .background(MaterialTheme.colorScheme.inversePrimary)
            .clickable {
                navController.navigate(Routes.Profile.EditProfile.name)
            }
    ) {
        Text(
            text = "Hello $name!",
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileDetailsPreview() {
    JetpackComposeDemoTheme {
        val navController: NavHostController = rememberNavController()
        ProfileDetailsScreen("Android", navController = navController)
    }
}