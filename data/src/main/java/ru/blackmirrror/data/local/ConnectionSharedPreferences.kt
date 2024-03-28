package ru.blackmirrror.data.local

import android.content.Context
import android.content.SharedPreferences

class ConnectionSharedPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var lastLoading: Long
        get() = sharedPreferences.getLong(KEY_LAST_LOADING, 0)
        set(value) {
            sharedPreferences.edit().putLong(KEY_LAST_LOADING, value).apply()
        }

    companion object {
        private const val PREFS_NAME = "connectionDataPrefs"
        private const val KEY_LAST_LOADING = "last_loading"
    }
}