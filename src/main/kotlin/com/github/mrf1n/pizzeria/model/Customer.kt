package com.github.mrf1n.pizzeria.model

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Customer(
    @Id
    val email: String,
    @ElementCollection
    val toppings: Set<String> = setOf()
)