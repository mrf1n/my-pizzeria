package com.github.mrf1n.pizzeria.service

import com.github.mrf1n.pizzeria.model.request.AuthenticationRequest
import com.github.mrf1n.pizzeria.model.response.AuthenticationResponse
import jakarta.servlet.http.HttpServletRequest

/**
 * Looks like this service hase real implementation security logic,
 * but it's stub for supporting "/toppings/personal" endpoint with
 * credentials from request to save time
 */
interface AuthenticationService {
    fun authentication(request: AuthenticationRequest): AuthenticationResponse

    fun getEmail(request:HttpServletRequest): String?
}