package com.jetpackcopmosedemo.presentation.ui.login

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jetpackcopmosedemo.presentation.common.login_components.LoginField
import com.jetpackcopmosedemo.presentation.common.login_components.PasswordField
import com.jetpackcopmosedemo.presentation.navigation.Routes
import com.jetpackcopmosedemo.presentation.theme.BaseColors
import com.jetpackcopmosedemo.utils.isValidEmail
import com.jetpackcopmosedemo.utils.isValidPassword

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current

    Surface {
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        localFocusManager.clearFocus()
                    })
                }
        ) {
            item {
                LoginField(
                    value = viewModel.emailText.collectAsState().value,
                    label = "Email",
                    onChange = {
                        viewModel.emailText.value = it
                        viewModel.isEmailTextError.value = viewModel.emailText.value.isValidEmail()
                    },
                    isError = viewModel.isEmailTextError.collectAsState().value == true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }
            item {
                PasswordField(
                    value = viewModel.passwordText.collectAsState().value ?: "",
                    onChange = {
                        viewModel.passwordText.value = it
                        viewModel.isPasswordTextError.value =
                            viewModel.passwordText.value.isValidPassword()
                    },
                    isError = viewModel.isPasswordTextError.collectAsState().value == true,
                    submit = {
                        if (viewModel.emailText.value.isValidEmail() && viewModel.passwordText.value.isValidPassword()) {
                            viewModel.isPasswordTextError.value = true
                            viewModel.isEmailTextError.value = true
                        } else if (!viewModel.emailText.value.isValidEmail()) {
                            if (!viewModel.passwordText.value.isValidPassword()) {
                                viewModel.startMockServer(onSuccess = {
                                    Handler(Looper.getMainLooper()).post {
                                        navController.navigate(Routes.Home.name) {
                                            popUpTo(Routes.Auth.name) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                }, onError = { message ->
                                    Handler(Looper.getMainLooper()).post {
                                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                    }
                                })
                            } else {
                                viewModel.isPasswordTextError.value = true
                            }
                        } else {
                            viewModel.isEmailTextError.value = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }
            item {
                ElevatedButton(
                    onClick = {
                        if (viewModel.emailText.value.isValidEmail() && viewModel.passwordText.value.isValidPassword()) {
                            viewModel.isPasswordTextError.value = true
                            viewModel.isEmailTextError.value = true
                        } else if (!viewModel.emailText.value.isValidEmail()) {
                            if (!viewModel.passwordText.value.isValidPassword()) {
                                viewModel.startMockServer(onSuccess = {
                                    Handler(Looper.getMainLooper()).post {
                                        navController.navigate(Routes.Home.name) {
                                            popUpTo(Routes.Auth.name) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                }, onError = { message ->
                                    Handler(Looper.getMainLooper()).post {
                                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                    }
                                })
                            } else {
                                viewModel.isPasswordTextError.value = true
                            }
                        } else {
                            viewModel.isEmailTextError.value = true
                        }
                    },
                    elevation = ButtonDefaults.elevatedButtonElevation(),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = BaseColors.Red
                    )
                ) {
                    Text(
                        text = "Login",
                        modifier = Modifier,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                }
            }
        }
    }
}
