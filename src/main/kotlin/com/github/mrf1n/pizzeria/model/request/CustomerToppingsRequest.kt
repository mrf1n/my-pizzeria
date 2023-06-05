package com.github.mrf1n.pizzeria.model.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull

data class CustomerToppingsRequest(
    @field:Email
    val email: String,
    @field:NotNull(message = "toppings must be specified")
    val toppings: Set<String>
)