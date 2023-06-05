package com.github.mrf1n.pizzeria.service

import com.github.mrf1n.pizzeria.model.Customer
import com.github.mrf1n.pizzeria.model.Topping
import com.github.mrf1n.pizzeria.repository.CustomerRepository
import com.github.mrf1n.pizzeria.repository.ToppingRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class RepositoryToppingsService(
    private val customerRepository: CustomerRepository,
    private val toppingRepository: ToppingRepository
) : ToppingsService {

    companion object : KLogging()

    override fun addToppings(customerEmail: String, toppings: Set<String>) {
        logger.info("Adding customer $customerEmail with topping $toppings")
        val customer = customerRepository.findByEmail(customerEmail.lowercase())
            ?.also {
                it.toppings.clear()
            } ?: Customer.of(customerEmail)

        toppings.forEach { toppingName ->
            val lowerCaseToppingName = toppingName.lowercase()
            val topping = toppingRepository.findByName(lowerCaseToppingName)
                ?: toppingRepository.save(Topping.of(lowerCaseToppingName))
            customer.toppings.add(topping)
        }

        customerRepository.save(customer)
    }

    override fun getToppings(): Map<String, Int> {
        val toppings = toppingRepository.findAll()
        return toppings.associateWith { topping ->
            customerRepository.countByToppingsContaining(topping)
        }.mapKeys { it.key.name }
    }

    override fun getToppingsForCustomer(email: String): Set<String> =
        customerRepository.findByEmail(email)
            ?.toppings
            ?.map { it.name }
            ?.toSet()
            .orEmpty()
}