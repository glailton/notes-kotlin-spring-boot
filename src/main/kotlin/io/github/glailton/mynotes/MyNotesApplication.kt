package io.github.glailton.mynotes

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyNotesApplication

fun main(args: Array<String>) {
    runApplication<MyNotesApplication>(*args)
//    SpringApplication.run(MyNotesApplication::class.java, *args)
}
