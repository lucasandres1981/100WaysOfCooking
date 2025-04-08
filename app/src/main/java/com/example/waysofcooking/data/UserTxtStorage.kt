package com.example.waysofcooking.data

import android.content.Context
import java.io.File


object UserTxtStorage {
    private const val FILE_NAME = "usuarios.txt"

    fun saveUser(context: Context, user: User): Boolean {
        val file = File(context.filesDir, FILE_NAME)

        // para evitar duplicados
        if (file.exists()) {
            val lines = file.readLines()
            if (lines.any { it.contains(user.email) }) {
                return false
            }
        }

        val line = "${user.fullName},${user.email},${user.password}\n"
        file.appendText(line)
        return true
    }

    fun userExists(context: Context, email: String, password: String): Boolean {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return false

        return file.readLines().any {
            val parts = it.split(",")
            parts.size == 3 && parts[1] == email && parts[2] == password
        }
    } // ← ESTA llave faltaba

    fun getUserByEmail(context: Context, email: String): User? {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return null

        return file.readLines()
            .mapNotNull { line ->
                val parts = line.split(",")
                if (parts.size >= 3 && parts[1] == email) {
                    User(parts[0], parts[1], parts[2]) // fullName, email, password
                } else null
            }.firstOrNull()
    }
}
