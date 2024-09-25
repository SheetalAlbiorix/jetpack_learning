package com.jetpackcopmosedemo.presentation.ui.login.login_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    label: String = "Password",
    submit: (() -> Unit)
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    val leadingIcon = @Composable {
        Icon(
            Icons.Outlined.Key,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.inversePrimary
        )
    }
    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
                if (isPasswordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.inversePrimary
            )
        }
    }

    Column {
        TextField(value = value,
            onValueChange = onChange,
            modifier = modifier,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    this.defaultKeyboardAction(ImeAction.Done)
                    submit()
                }
            ),
            singleLine = true,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            label = {
                Text(
                    text = label,
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.inversePrimary
                )
            },
            isError = isError
        )
        Text(
            text = if (isError) "Please enter valid password" else "",
            modifier = modifier.padding(start = 16.dp),
            color = MaterialTheme.colorScheme.inversePrimary
        )
    }
}