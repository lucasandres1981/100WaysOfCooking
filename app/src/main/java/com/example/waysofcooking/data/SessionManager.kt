package com.example.waysofcooking.data
import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREF_NAME = "user_session"
    private const val KEY_EMAIL = "logged_email"

    fun saveUserEmail(context: Context, email: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_EMAIL, email).apply()
    }

    fun getLoggedUserEmail(context: Context): String? {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_EMAIL, null)
    }

    fun clearSession(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }
}
