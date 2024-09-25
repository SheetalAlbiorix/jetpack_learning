package com.jetpackcopmosedemo.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHelper(private val context: Context) {
    /** Initialize
     * [sharedPreferences] instance
     */
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("Preferences", Context.MODE_PRIVATE)

    //region String
    fun saveString(key: Int, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(context.resources.getString(key), value)
        editor.apply()
    }

    fun getString(key: Int, defaultValue: String): String {
        return sharedPreferences.getString(context.resources.getString(key), defaultValue)
            ?: defaultValue
    }
    //endregion

    //region Boolean
    fun saveBoolean(key: Int, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(context.resources.getString(key), value)
        editor.apply()
    }

    fun getBoolean(key: Int, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(context.resources.getString(key), defaultValue)
    }
    //endregion

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}
