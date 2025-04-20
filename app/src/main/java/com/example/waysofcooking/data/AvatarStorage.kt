package com.example.waysofcooking.data

import android.content.Context

object AvatarStorage {
    private const val PREFS_NAME = "avatar_prefs"
    private const val KEY_AVATAR = "selected_avatar"

    fun saveAvatar(context: Context, avatarResName: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_AVATAR, avatarResName).apply()
    }

    fun getAvatar(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_AVATAR, "avatar_1") ?: "avatar_1"
    }
}
