package com.github.mrf1n.pizzeria.model

data class Customer(
    val email: String,
    val toppings: Set<String> = setOf()
)