package com.github.mrf1n.pizzeria.service

import com.github.mrf1n.pizzeria.model.Customer

interface ToppingsService {

    fun addToppings(customer: Customer)

    fun getToppings(): Map<String, Int>

    fun getToppingsForCustomer(email: String): Set<String>
}