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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Request

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

fun Request.getRequestBodyAsJson(): Map<Any, Any>? {
    val buffer = okio.Buffer()
    this.body?.writeTo(buffer)
    val type = object : TypeToken<Map<Any, Any>>() {}.type
    val finalRequest = buffer.readUtf8()
    return Gson().fromJson<Map<Any, Any>>(finalRequest, type)
}

fun Map<Any, Any>.getMessageFromMap(): String? {
    val type = object : TypeToken<Map<Any, Any>>() {}.type
    val jsonString = entries.joinToString(prefix = "{", postfix = "}") {
        "\"${it.key}\": \"${it.value}\""
    }
    return Gson().fromJson<Map<Any, Any>>(jsonString, type)["message"].toString()
}