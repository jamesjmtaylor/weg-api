package api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class Api

fun main(args: Array<String>) {
	SpringApplication.run(Api::class.java, *args)
}
