package com.jetpackcopmosedemo.presentation.ui.profile

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.jetpackcopmosedemo.presentation.theme.JetpackComposeDemoTheme

@Composable
fun EditProfileScreen(
    name: String,
    modifier: Modifier = Modifier,
    navController: NavHostController? = null
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.inversePrimary
    )
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    JetpackComposeDemoTheme {
        EditProfileScreen("Android")
    }
}