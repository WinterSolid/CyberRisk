package com.wintersolid.cyberrisk.viewmodel

import com.wintersolid.cyberrisk.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.function.Consumer

class LoginViewModelTest {
    // 'lateinit' allows initializing a not-null
    private lateinit var userRepository: UserRepository
    private lateinit var viewModel: LoginViewModel

    @BeforeEach
    fun setUp() {
        userRepository = UserRepository()
        viewModel = LoginViewModel(userRepository)
    }

    @Test
    fun testLoginSuccess() {
        viewModel.username = "testUser"
        viewModel.password = "testPassword"

        viewModel.login(Consumer { success ->
            assertTrue(success)
        })
    }

    @Test
    fun testLoginFailure() {
        viewModel.username = "wrongUser"
        viewModel.password = "wrongPassword"

        viewModel.login(Consumer { success ->
            assertFalse(success)
        })
    }
}