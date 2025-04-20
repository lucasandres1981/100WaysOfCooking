package com.example.waysofcooking.data

import android.content.Context
import org.json.JSONObject
import java.io.File

object FavoriteRecipesStorage {
    private const val FILE_NAME = "favorite_recipes.json"

    fun getFavorites(context: Context, nickName: String): List<String> {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return emptyList()

        val json = JSONObject(file.readText())
        val favArray = json.optJSONArray(nickName) ?: return emptyList()
        return List(favArray.length()) { i -> favArray.getString(i) }
    }

    fun saveFavorite(context: Context, nickName: String, nombreId: String) {
        val file = File(context.filesDir, FILE_NAME)
        val json = if (file.exists()) JSONObject(file.readText()) else JSONObject()

        val favList = getFavorites(context, nickName).toMutableSet()
        favList.add(nombreId)

        json.put(nickName, org.json.JSONArray(favList.toList()))
        file.writeText(json.toString())
    }

    fun removeFavorite(context: Context, nickName: String, nombreId: String) {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return

        val json = JSONObject(file.readText())
        val favList = getFavorites(context, nickName).toMutableSet()
        favList.remove(nombreId)

        json.put(nickName, favList.toTypedArray())
        file.writeText(json.toString())
    }

    fun isFavorite(context: Context, nickName: String, nombreId: String): Boolean {
        return getFavorites(context, nickName).contains(nombreId)
    }
}
