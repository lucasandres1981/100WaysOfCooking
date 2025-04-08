package com.example.waysofcooking.data

object UserRepository {
    private val users = mutableListOf<User>()

    fun registerUser(user: User): Boolean {
        // para verificar si el correo existe
        if (users.any { it.email == user.email }) return false
        users.add(user)
        return true
    }

    fun login(email: String, password: String): Boolean {
        return users.any { it.email == email && it.password == password }
    }
}
