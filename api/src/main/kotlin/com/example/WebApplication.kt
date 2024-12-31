package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.example")
class WebApplication

fun main(args: Array<String>) {
    runApplication<WebApplication>(*args)
}