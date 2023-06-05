package com.github.mrf1n.pizzeria.repository

import com.github.mrf1n.pizzeria.model.Topping
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ToppingRepository : JpaRepository<Topping, Long> {

    fun findByName(name: String): Topping?
}