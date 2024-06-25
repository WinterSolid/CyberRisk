package com.wintersolid.cyberrisk.viewmodel

import com.wintersolid.cyberrisk.repository.UserRepository
import java.util.function.Consumer

class LoginViewModel(private val userRepository: UserRepository) {

    var username: String = ""
    var password: String = ""

    /**
     * Performs login operation.
     * @param callback Callback function to be called with login result
     * (true for success, false for failure).
     */
    fun login(callback: Consumer<Boolean>) {
        // Validate username and password
        if (username.isNotBlank() && password.isNotBlank()) {
            // Check if the user exists in the repository
            val user = userRepository.getUser(username)
            if (user != null && user.password == password) {
                callback.accept(true) // Login successful
                return
            }
        }
        callback.accept(false) // Login failed
    }
}
