package com.jetpackcopmosedemo.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.jetpackcopmosedemo.presentation.common.bottomBar
import com.jetpackcopmosedemo.presentation.ui.home.HomeScreen
import com.jetpackcopmosedemo.presentation.ui.login.LoginScreen
import com.jetpackcopmosedemo.presentation.ui.profile.EditProfileScreen
import com.jetpackcopmosedemo.presentation.ui.profile.ProfileDetailsScreen
import com.jetpackcopmosedemo.presentation.ui.profile.ProfileScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    Scaffold(
        bottomBar = { bottomBar(navController) }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController, startDestination = Routes.Auth.name
        ) {
            composable(Routes.Home.name) {
                HomeScreen(navController = navController, name = "Home")
            }
            navigation(
                route = Routes.Profile.name,
                startDestination = Routes.Profile.Profiles.name,
            ) {
                composable(Routes.Profile.Profiles.name) {
                    ProfileScreen(navController = navController, name = "Profile")
                }
                composable(Routes.Profile.Details.name) {
                    val userId = it.arguments?.getString("userId")
                    ProfileDetailsScreen(navController = navController, name = "Details $userId")
                }
                composable(Routes.Profile.EditProfile.name) {
                    EditProfileScreen(navController = navController, name = "Edit Profile")
                }
            }
            navigation(
                route = Routes.Auth.name,
                startDestination = Routes.Auth.Login.name,
            ) {
                composable(Routes.Auth.Login.name) {
                    LoginScreen(navController = navController)
                }
                composable(Routes.Auth.ForgotPassword.name) {
                    val userId = it.arguments?.getString("userId")
                    ProfileDetailsScreen(navController = navController, name = "Details $userId")
                }
                composable(Routes.Auth.Register.name) {
                    EditProfileScreen(navController = navController, name = "Register")
                }
            }

            dialog(Routes.Alerts.Logout.name) {
                AlertDialog(title = {
                    Text(
                        text = "Alert",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                }, text = {
                    Text(
                        text = "Are you sure want to logout?",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                }, onDismissRequest = {
                    navController.navigateUp()
                }, confirmButton = {
                    ElevatedButton(onClick = { navController.navigateUp() }) {
                        Text(
                            text = "Logout",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.inversePrimary
                        )
                    }
                })
            }
        }
    }
}