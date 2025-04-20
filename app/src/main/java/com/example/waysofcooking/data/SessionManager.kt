package com.example.waysofcooking.data

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREF_NAME = "user_session"
    private const val KEY_NICK = "logged_user_nick"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // Guarda el nickName (no el email)
    fun saveUserNickName(context: Context, nickName: String) {
        val editor = getPrefs(context).edit()
        editor.putString(KEY_NICK, nickName)
        editor.apply()
    }

    // Obtiene el nickName
    fun getLoggedUserNick(context: Context): String? {
        return getPrefs(context).getString(KEY_NICK, null)
    }

    // Borra la sesión
    fun clearSession(context: Context) {
        val editor = getPrefs(context).edit()
        editor.clear()
        editor.apply()
    }
}
