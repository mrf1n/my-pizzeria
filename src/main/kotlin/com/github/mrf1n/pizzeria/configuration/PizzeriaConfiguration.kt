package com.github.mrf1n.pizzeria.configuration

import com.github.mrf1n.pizzeria.repository.CustomerRepository
import com.github.mrf1n.pizzeria.service.InMemoryToppingsService
import com.github.mrf1n.pizzeria.service.RepositoryToppingsService
import com.github.mrf1n.pizzeria.service.ToppingsService
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PizzeriaConfiguration {

    @Bean
    @ConditionalOnProperty(
        prefix = "pizzeria.storage",
        value = ["type"],
        havingValue = "inmemory",
        matchIfMissing = true
    )
    fun toppingsServiceInMemory(): ToppingsService = InMemoryToppingsService()

    @Bean
    @ConditionalOnProperty(prefix = "pizzeria.storage", value = ["type"], havingValue = "repository")
    fun toppingsServiceRepository(customerRepository: CustomerRepository): ToppingsService =
        RepositoryToppingsService(customerRepository)
}