package com.github.mrf1n.pizzeria.service

import com.github.mrf1n.pizzeria.model.request.AuthenticationRequest
import com.github.mrf1n.pizzeria.model.response.AuthenticationResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service

@Service
class JwtAuthenticationService(
    val jwtService: JwtService
) : AuthenticationService {

    override fun authentication(request: AuthenticationRequest): AuthenticationResponse {
        val jwtToken: String = jwtService.generateToken(request.username)
        return AuthenticationResponse(jwtToken)
    }

    override fun getEmail(request: HttpServletRequest): String? {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring(7)
            return jwtService.extractUsername(token)
        }
        return null
    }
}