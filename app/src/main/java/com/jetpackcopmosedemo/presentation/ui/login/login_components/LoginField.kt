package com.jetpackcopmosedemo.presentation.ui.login.login_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginField(
    value: String?,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    label: String = "Email",
) {

    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Outlined.Person,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.inversePrimary
        )
    }

    Column {
        TextField(
            value = value ?: "",
            onValueChange = onChange,
            modifier = modifier,
            leadingIcon = leadingIcon,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            label = {
                Text(
                    text = label,
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.inversePrimary
                )
            },
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            isError = isError,
        )
        Text(
            text = if (isError) "Please enter valid email" else "",
            modifier = modifier.padding(start = 16.dp),
            color = MaterialTheme.colorScheme.inversePrimary
        )
    }
}