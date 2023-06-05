package com.github.mrf1n.pizzeria.repository

import com.github.mrf1n.pizzeria.model.Customer
import com.github.mrf1n.pizzeria.model.Topping
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, String> {

    fun countByToppingsContaining(topping: Topping): Int

    @EntityGraph(attributePaths = ["toppings"])
    fun findByEmail(email: String): Customer?
}