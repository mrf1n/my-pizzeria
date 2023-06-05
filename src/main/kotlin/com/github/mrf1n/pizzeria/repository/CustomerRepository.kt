package com.github.mrf1n.pizzeria.repository

import com.github.mrf1n.pizzeria.model.Customer
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, String> {

    @Query(
        """
        SELECT t.name, count(c.id)
        FROM Customer c
        JOIN c.toppings t
        GROUP BY t
    """
    )
    fun countByToppings(): List<Array<Any>>

    @EntityGraph(attributePaths = ["toppings"])
    fun findByEmail(email: String): Customer?
}