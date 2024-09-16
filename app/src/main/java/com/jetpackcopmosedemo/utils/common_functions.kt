package com.jetpackcopmosedemo.utils

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role

var lastClickTime = 0L

fun onSingleClick(onClick: () -> Unit): () -> Unit = {
    if ((System.currentTimeMillis() - lastClickTime) > 1000L) {
        lastClickTime = System.currentTimeMillis()
        onClick.invoke()
    }
}

fun Modifier.clickableSingle(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    val localIndication = LocalIndication.current
    val interactionSource = if (localIndication is IndicationNodeFactory) {
        null
    } else {
        remember { MutableInteractionSource() }
    }
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = onSingleClick { onClick.invoke() },
        role = role,
        indication = localIndication,
        interactionSource = interactionSource
    )
}