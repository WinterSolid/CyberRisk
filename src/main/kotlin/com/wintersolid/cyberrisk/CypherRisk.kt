package com.wintersolid.cyberrisk

import org.springframework.boot.autoconfigure.SpringBootApplication
import java.io.IOException
import java.util.*

@SpringBootApplication
class CypherRisk(private val viewModel: LoginViewModel) {

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
					handleFileOperations(scanner)
				} catch (e: IOException) {
					System.err.println("File operation failed: " + e.message)
				}
			} else {
				println("Login failed. Please check your credentials.")
			}
		}

		scanner.close()
	}
