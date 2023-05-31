package com.github.mrf1n.pizzeria.repository

import com.github.mrf1n.pizzeria.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository : JpaRepository<Customer, String> {

    @Query("SELECT DISTINCT t FROM Customer c JOIN c.toppings t")
    fun findAllToppings(): List<String>

    fun countByToppingsContaining(topping: String): Int

    @Query("SELECT c FROM Customer c JOIN FETCH c.toppings WHERE c.id = :id")
    fun findByIdWithToppings(@Param("id") id: String): Optional<Customer>
}