package com.jetpackcopmosedemo.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jetpackcopmosedemo.presentation.navigation.BottomNavItem
import com.jetpackcopmosedemo.presentation.navigation.Routes
import com.jetpackcopmosedemo.presentation.theme.BaseColors
import com.jetpackcopmosedemo.presentation.theme.JetpackComposeDemoTheme

val bottomBar: @Composable (NavHostController) -> Unit = { navController ->
    val screens = listOf(
        BottomNavItem.Home, BottomNavItem.Profile, BottomNavItem.LogoutAlert,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    AnimatedVisibility(visible = currentRoute?.contains("Auth") != true) {
        NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
            screens.forEach { screen ->
                val isSelected = currentRoute?.contains(screen.route)
                NavigationBarItem(
                    selected = isSelected ?: false,
                    label = { Text(text = screen.label) },
                    icon = {
                        Icon(
                            imageVector = if (isSelected == true) screen.selectedIcon else screen.unSelectedIcon,
                            contentDescription = screen.label,
                        )
                    },
                    onClick = {
                        navController.navigate(screen.route) {
                            if (!screen.route.contains(Routes.Alerts.name)) {
                                navController.graph.findStartDestination().route?.let {
                                    popUpTo(it) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        unselectedTextColor = MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.8f),
                        unselectedIconColor = MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.8f),
                        selectedTextColor = MaterialTheme.colorScheme.inversePrimary,
                        selectedIconColor = MaterialTheme.colorScheme.inversePrimary,
                        indicatorColor = BaseColors.Transparent,
                    ),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeDemoTheme {
        val navController: NavHostController = rememberNavController()
        bottomBar(navController)
    }
}