package com.example.waysofcooking.data

import android.content.Context
import org.json.JSONObject
import java.io.File

object UserPreferencesStorage {

    private const val FILE_NAME = "user_preferences.json"

    fun getPreferences(context: Context, nickName: String): Map<String, String>? {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return null

        val json = JSONObject(file.readText())
        if (!json.has(nickName)) return null

        val userPrefs = json.getJSONObject(nickName)
        return mapOf(
            "diet" to userPrefs.optString("diet", ""),
            "allergies" to userPrefs.optString("allergies", "")
        )
    }

    fun savePreferences(context: Context, nickName: String, diet: String, allergies: String) {
        val file = File(context.filesDir, FILE_NAME)
        val json = if (file.exists()) JSONObject(file.readText()) else JSONObject()

        val prefs = JSONObject().apply {
            put("diet", diet)
            put("allergies", allergies)
        }

        json.put(nickName, prefs)
        file.writeText(json.toString())
    }
}

