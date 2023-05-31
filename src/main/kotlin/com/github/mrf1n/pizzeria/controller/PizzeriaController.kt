package com.github.mrf1n.pizzeria.controller

import com.github.mrf1n.pizzeria.model.Customer
import com.github.mrf1n.pizzeria.service.ToppingsService
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/toppings")
class PizzeriaController(private val toppingsService: ToppingsService) {

    companion object : KLogging()

    @Value("\${personal.email}")
    private lateinit var personalEmail: String

    @PostMapping
    fun addToppings(@Valid @NotNull @RequestBody requestBody: Customer) {
        logger.info("Adding customer $requestBody")
        toppingsService.addToppings(requestBody)
    }

    @GetMapping
    fun getToppings(): Map<String, Int> = toppingsService.getToppings()

    @GetMapping("/{email}")
    fun getToppingsByEmail(@Valid @Email @PathVariable("email") email: String): Set<String> =
        toppingsService.getToppingsForCustomer(email)

    @GetMapping("/special")
    fun getSpecialToppings(): Set<String> = toppingsService.getToppingsForCustomer(personalEmail)
}
