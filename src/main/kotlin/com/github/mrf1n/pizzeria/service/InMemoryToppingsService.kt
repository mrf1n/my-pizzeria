package com.github.mrf1n.pizzeria.service

import com.github.mrf1n.pizzeria.model.Customer
import mu.KLogging
import java.util.concurrent.ConcurrentHashMap

class InMemoryToppingsService: ToppingsService {
    private val customersToppings = ConcurrentHashMap<String, Customer>()
    private val toppings = ConcurrentHashMap<String, Int>()

    companion object : KLogging()

    override fun addToppings(customer: Customer) {
        logger.info("$customer")
        customersToppings[customer.email] = customer
        toppings.clear()
    }

    override fun getToppings(): Map<String, Int> {
        if (toppings.isEmpty()) {
            customersToppings.values.forEach { customerToppings ->
                customerToppings.toppings.forEach {topping ->
                    toppings[topping] = toppings.getOrDefault(topping, 0) + 1
                }
            }
        }
        return toppings
    }

    override fun getToppingsForCustomer(email: String?): Set<String> = customersToppings[email]?.toppings ?: emptySet()
}