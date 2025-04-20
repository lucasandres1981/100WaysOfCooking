package com.example.waysofcooking.data

import android.content.Context
import java.io.File

object UserTxtStorage {
    private const val FILE_NAME = "usuarios.txt"

    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".") && email.length > 5
    }

    fun saveUser(context: Context, user: User): Boolean {
        val file = File(context.filesDir, FILE_NAME)

        if (!isValidEmail(user.email)) return false

        // para evitar duplicados por correo o nickname
        if (file.exists()) {
            val lines = file.readLines()
            if (lines.any { it.contains(user.email, ignoreCase = true) || it.contains(user.nickName) }) {
                return false
            }
        }

        val line = "${user.nickName},${user.fullName},${user.email},${user.password}\n"
        file.appendText(line)
        return true
    }

    fun userExists(context: Context, email: String, password: String): Boolean {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return false

        return file.readLines().any {
            val parts = it.split(",")
            parts.size == 4 && parts[2] == email && parts[3] == password
        }
    }

    fun getUserByNick(context: Context, nickName: String): User? {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return null

        return file.readLines()
            .mapNotNull { line ->
                val parts = line.split(",")
                if (parts.size == 4 && parts[0] == nickName) {
                    User(parts[0], parts[1], parts[2], parts[3])
                } else null
            }.firstOrNull()
    }

    fun getUserByEmailAndPassword(context: Context, email: String, password: String): User? {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return null

        return file.readLines()
            .mapNotNull { line ->
                val parts = line.split(",")
                if (parts.size == 4 && parts[2] == email && parts[3] == password) {
                    User(parts[0], parts[1], parts[2], parts[3])
                } else null
            }.firstOrNull()
    }
}
