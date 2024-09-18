package com.jetpackcopmosedemo.app.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jetpackcopmosedemo.presentation.navigation.NavigationGraph
import com.jetpackcopmosedemo.presentation.theme.JetpackComposeDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeDemoTheme {
                val navController: NavHostController = rememberNavController()
                NavigationGraph(navController = navController)
            }
        }
    }
}