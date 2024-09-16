package com.jetpackcopmosedemo.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Routes(val name: String) {
    data object Home : Routes("Home")
    data object Alerts : Routes("Alerts") {
        data object Logout : Routes("Alerts.Logout")
    }

    data object Auth : Routes("Auth") {
        data object Login : Routes("Auth.Login")
        data object ForgotPassword : Routes("Auth.ForgotPassword/{userId}") {
            fun createRoute(userId: String) = "Auth.ForgotPassword/$userId"
        }

        data object Register : Routes("Auth.Register")
    }

    data object Profile : Routes("Profile") {
        data object Profiles : Routes("Profile.Profiles")
        data object Details : Routes("Profile.Detail/{userId}") {
            fun createRoute(userId: String) = "Profile.Detail/$userId"
        }

        data object EditProfile : Routes("Profile.Edit")
    }
}

sealed class BottomNavItem(
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val label: String
) {
    data object Home :
        BottomNavItem(Routes.Home.name, Icons.Default.Home, Icons.Outlined.Home, "Home")

    data object Profile :
        BottomNavItem(Routes.Profile.name, Icons.Default.Person, Icons.Outlined.Person, "Profile")

    data object LogoutAlert :
        BottomNavItem(
            Routes.Alerts.Logout.name,
            Icons.AutoMirrored.Default.ExitToApp,
            Icons.AutoMirrored.Default.ExitToApp,
            "Logout"
        )
}