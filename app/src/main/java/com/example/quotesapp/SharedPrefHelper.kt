package com.example.quotesapp.helpers

import android.content.Context
import android.content.SharedPreferences

class SharedPrefHelper(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("quotes_prefs", Context.MODE_PRIVATE)

    fun saveString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    fun deleteValue(key: String) {
        prefs.edit().remove(key).apply()
    }

    companion object {
        const val KEY_FAV_QUOTE = "fav_quote"
        const val KEY_TIMESTAMP = "fav_timestamp"
    }
}
