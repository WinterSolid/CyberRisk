package com.wintersolid.cyberrisk

import com.wintersolid.cyberrisk.repository.UserRepository
import com.wintersolid.cyberrisk.viewmodel.LoginViewModel
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.io.IOException
import java.util.*

@SpringBootApplication
class CypherRisk(private val viewModel: LoginViewModel) {
	// Companion object for portability between Kotlin -> java
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			val userRepository = UserRepository()
			val viewModel = LoginViewModel(userRepository)
			val cypherRisk = CypherRisk(viewModel)

			cypherRisk.start()
		}
	}

	fun start() {
		val scanner = Scanner(System.`in`)

		print("Enter your username: ")
		val username = scanner.nextLine()
		viewModel.username = username

		print("Enter your password: ")
		val password = scanner.nextLine()
		viewModel.password = password

		viewModel.login { success ->
			if (success) {
				println("Login successful!")
				try {
					handleFileOperations(scanner)// Todo handleFileOperations
				} catch (e: IOException) {
					System.err.println("File operation failed: " + e.message)
				}
			} else {
				println("Login failed. Please check your credentials.")
			}
		}

		scanner.close()
	}
}

