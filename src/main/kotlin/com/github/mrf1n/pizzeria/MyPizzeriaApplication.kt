package com.github.mrf1n.pizzeria

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyPizzeriaApplication

fun main(args: Array<String>) {
    runApplication<MyPizzeriaApplication>(*args)
}