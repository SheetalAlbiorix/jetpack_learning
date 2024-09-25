package com.jetpackcopmosedemo.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jetpackcopmosedemo.presentation.theme.BaseColors

@Composable
fun CommonCircularIndicator() {
    return Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BaseColors.Black.copy(alpha = 0.3f))
            .clickable(
                onClick = {},
                interactionSource = null,
                indication = null
            )
    ) {
        CircularProgressIndicator(
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .size(32.dp)
        )
    }
}
