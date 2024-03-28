package ru.blackmirrror.currency.data.local

import android.content.Context
import android.content.SharedPreferences
import java.util.Date

class ConnectionSharedPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var connection: Boolean
        get() = sharedPreferences.getBoolean(KEY_CONNECTION, false)
        set(value) {
            sharedPreferences.edit().putBoolean(KEY_CONNECTION, value).apply()
        }

    var lastLoading: Long
        get() = sharedPreferences.getLong(KEY_LAST_LOADING, 0)
        set(value) {
            sharedPreferences.edit().putLong(KEY_LAST_LOADING, value).apply()
        }

    fun clearPreferences() {
        sharedPreferences.edit().remove(KEY_CONNECTION).apply()
    }

    companion object {
        private const val PREFS_NAME = "connectionDataPrefs"
        private const val KEY_CONNECTION = "connection"
        private const val KEY_LAST_LOADING = "last_loading"
    }
}