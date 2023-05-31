package com.github.mrf1n.pizzeria.repository

import com.github.mrf1n.pizzeria.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, String> {

    @Query("SELECT DISTINCT t FROM Customer c JOIN c.toppings t")
    fun findAllToppings(): List<String>

    fun countByToppingsContaining(topping: String): Int
}