package com.wintersolid.cyberrisk

import com.wintersolid.cyberrisk.repository.UserRepository
import com.wintersolid.cyberrisk.viewmodel.LoginViewModel
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@SpringBootApplication
class CypherRisk(private val viewModel: LoginViewModel) {

	fun start() {
		val scanner = Scanner(System.`in`)

		var loggedIn = false
		while (!loggedIn) {
			print("Enter your username: ")
			val username = scanner.nextLine()
			viewModel.username = username

			print("Enter your password: ")
			val password = scanner.nextLine()
			viewModel.password = password

			viewModel.login { success ->
				if (success) {
					println("Login successful!")
					loggedIn = true
					try {
						handleFileOperations(scanner)
					} catch (e: IOException) {
						System.err.println("File operation failed: " + e.message)
					}
				} else {
					println("Login failed. Please check your credentials and try again.")
				}
			}
		}

		scanner.close()
	}

	@Throws(IOException::class)
	private fun handleFileOperations(scanner: Scanner) {
		var validFile = false
		var fileName = ""

		while (!validFile) {
			print("Enter the name of the text file: ")
			fileName = scanner.nextLine()

			val filePath = Paths.get("src/main/resource/$fileName")

			if (Files.exists(filePath)) {
				validFile = true
			} else {
				println("File does not exist. Please try again.")
			}
		}

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
		val filePath = Paths.get("src/main/resources/$fileName")
		val fileContent = Files.readAllBytes(filePath)
		val encryptedContent = Base64.getEncoder().encodeToString(fileContent)
		FileWriter(filePath.toString()).use { writer ->
			writer.write(encryptedContent)
		}
		println("File encrypted successfully.")
	}

	@Throws(IOException::class)
	private fun decryptFile(fileName: String) {
		val filePath = Paths.get("src/main/resources/$fileName")
		val fileContent = Files.readAllBytes(filePath)
		val decryptedContent = Base64.getDecoder().decode(String(fileContent))
		FileWriter(filePath.toString()).use { writer ->
			writer.write(String(decryptedContent))
		}
		println("File decrypted successfully.")
	}
}

fun main(args: Array<String>) {
	val userRepository = UserRepository()
	val viewModel = LoginViewModel(userRepository)
	val app = CypherRisk(viewModel)
	app.start()
}
