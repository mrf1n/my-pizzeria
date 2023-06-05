package com.github.mrf1n.pizzeria.service

interface ToppingsService {

    fun addToppings(customerEmail: String, toppings: Set<String>)

    fun getToppings(): Map<String, Long>

    fun getToppingsForCustomer(email: String): Set<String>
}