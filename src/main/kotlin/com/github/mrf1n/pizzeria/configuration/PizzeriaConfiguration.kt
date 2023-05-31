package com.github.mrf1n.pizzeria.configuration

import com.github.mrf1n.pizzeria.repository.CustomerRepository
import com.github.mrf1n.pizzeria.service.RepositoryToppingsService
import com.github.mrf1n.pizzeria.service.ToppingsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PizzeriaConfiguration {

    @Bean
    fun toppingsService(customerRepository: CustomerRepository): ToppingsService =
        RepositoryToppingsService(customerRepository)

//    @Bean
//    fun toppingsService(): ToppingsService = InMemoryToppingsService()
}