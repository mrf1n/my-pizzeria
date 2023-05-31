package com.github.mrf1n.pizzeria.model

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

@Entity
data class Customer(
    @Id
    @field:Email
    val email: String,
    @ElementCollection
    @field:NotEmpty(message = "toppings must not be empty")
    val toppings: Set<String> = setOf()
)