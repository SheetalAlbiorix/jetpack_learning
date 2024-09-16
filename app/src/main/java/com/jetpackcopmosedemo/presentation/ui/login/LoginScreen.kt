package com.jetpackcopmosedemo.presentation.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jetpackcopmosedemo.presentation.common.login_components.LoginField
import com.jetpackcopmosedemo.presentation.common.login_components.PasswordField
import com.jetpackcopmosedemo.presentation.navigation.Routes

@Composable
fun LoginScreen(
    navController: NavHostController
) {
    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            LoginField(
                value = "login",
                label = "login",
                onChange = { },
                modifier = Modifier.fillMaxWidth()
            )
            PasswordField(
                value = "password",
                onChange = { },
                submit = {

                },
                modifier = Modifier.fillMaxWidth()
            )
            ElevatedButton(onClick = { navController.navigate(Routes.Home.name) }) {
                Text(
                    text = "Login",
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.inversePrimary
                )
            }
        }
    }
}
