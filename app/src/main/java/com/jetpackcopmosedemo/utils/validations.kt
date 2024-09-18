package com.jetpackcopmosedemo.utils

import android.util.Patterns
import java.util.regex.Pattern


fun String?.isValidEmail(): Boolean {
    return this.isNullOrBlank() || !Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String?.isValidPassword(): Boolean {
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
    val pattern: Pattern = Pattern.compile(passwordPattern)
    return this.isNullOrBlank() || this.length < 6 || !pattern.matcher(this).matches()
}