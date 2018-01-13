package api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class KotlinSpringJpaPostgresqlApplication

fun main(args: Array<String>) {
	SpringApplication.run(KotlinSpringJpaPostgresqlApplication::class.java, *args)
}
