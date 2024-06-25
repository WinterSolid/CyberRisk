package com.wintersolid.cyberrisk

import com.wintersolid.cyberrisk.repository.UserRepository
import com.wintersolid.cyberrisk.viewmodel.LoginViewModel
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@SpringBootApplication
class CypherRisk {

	fun start(viewModel: LoginViewModel) {
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
					handleFileOperations(scanner, viewModel)
				} catch (e: IOException) {
					System.err.println("File operation failed: " + e.message)
				}
			} else {
				println("Login failed. Please check your credentials.")
			}
		}

		scanner.close()
	}

	@Throws(IOException::class)
	private fun handleFileOperations(scanner: Scanner, viewModel: LoginViewModel) {
		print("Enter the name of the text file: ")
		val fileName = scanner.nextLine()

		print("Do you want to (E)ncrypt or (D)ecrypt the file? ")
		val choice = scanner.nextLine().uppercase(Locale.getDefault())

		when (choice) {
			"E" -> encryptFile(fileName)
			"D" -> decryptFile(fileName)
			else -> println("Invalid choice.")
		}
	}

	@Throws(IOException::class)
	private fun encryptFile(fileName: String) {
		val fileContent = Files.readAllBytes(Paths.get(fileName))
		val encryptedContent = Base64.getEncoder().encodeToString(fileContent)
		FileWriter(fileName).use { writer ->
			writer.write(encryptedContent)
		}
		println("File encrypted successfully.")
	}

	@Throws(IOException::class)
	private fun decryptFile(fileName: String) {
		val fileContent = Files.readAllBytes(Paths.get(fileName))
		val decryptedContent = Base64.getDecoder().decode(String(fileContent))
		FileWriter(fileName).use { writer ->
			writer.write(String(decryptedContent))
		}
		println("File decrypted successfully.")
	}
}

fun main(args: Array<String>) {
	val userRepository = UserRepository()
	val viewModel = LoginViewModel(userRepository)
	val app = CypherRisk()
	app.start(viewModel)
}
