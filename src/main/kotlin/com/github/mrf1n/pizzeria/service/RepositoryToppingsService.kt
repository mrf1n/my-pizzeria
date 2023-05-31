package com.github.mrf1n.pizzeria.service

import com.github.mrf1n.pizzeria.model.Customer
import com.github.mrf1n.pizzeria.repository.CustomerRepository
import kotlin.jvm.optionals.getOrDefault

class RepositoryToppingsService(private val customerRepository: CustomerRepository) : ToppingsService {

    override fun addToppings(customer: Customer) {
        customerRepository.save(customer)
    }

    override fun getToppings(): Map<String, Int> {
        val toppings = customerRepository.findAllToppings()
        return toppings.associateWith { topping ->
            customerRepository.countByToppingsContaining(topping)
        }
    }

    override fun getToppingsForCustomer(email: String?): Set<String> =
        email?.let {
            customerRepository.findByEmail(it)
                .map(Customer::toppings)
                .getOrDefault(emptySet())
        } ?: emptySet()
}