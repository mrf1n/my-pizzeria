package com.github.mrf1n.pizzeria.controller

import com.github.mrf1n.pizzeria.model.request.CustomerToppingsRequest
import com.github.mrf1n.pizzeria.service.AuthenticationService
import com.github.mrf1n.pizzeria.service.ToppingsService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/toppings")
class PizzeriaController(
    private val toppingsService: ToppingsService,
    private val authenticationService: AuthenticationService
) {

    @PostMapping
    fun addToppings(@Valid @NotNull @RequestBody requestBody: CustomerToppingsRequest) {
        toppingsService.addToppings(requestBody.email, requestBody.toppings)
    }

    @GetMapping
    fun getToppings(): Map<String, Int> = toppingsService.getToppings()

    @GetMapping("/{email}")
    fun getToppingsByEmail(@Valid @Email @PathVariable("email") email: String): Set<String> =
        toppingsService.getToppingsForCustomer(email)

    @GetMapping("/personal")
    fun getSpecialToppings(request: HttpServletRequest): Set<String> =
        authenticationService.getEmail(request)
            ?.let { toppingsService.getToppingsForCustomer(it) }
            .orEmpty()
}
