package com.github.mrf1n.pizzeria.runner

import com.github.mrf1n.pizzeria.model.Customer
import com.github.mrf1n.pizzeria.service.ToppingsService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("personal")
class SpecialInitRunner(private val toppingsService: ToppingsService) : ApplicationRunner {

    lateinit var email: String
    lateinit var toppings: Set<String>

    override fun run(args: ApplicationArguments?) {
        toppingsService.getToppingsForCustomer(email).ifEmpty {
            toppingsService.addToppings(Customer(email, toppings))
        }
    }
}