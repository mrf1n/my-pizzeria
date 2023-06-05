package com.github.mrf1n.pizzeria.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.mrf1n.pizzeria.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        val jwt = authHeader.substring(7)
        try {
            val userEmail = jwtService.extractUsername(jwt)
            if (userEmail != null && SecurityContextHolder.getContext().authentication == null) {
                if (jwtService.isTokenValid(jwt)) {
                    val authToken = UsernamePasswordAuthenticationToken(
                        userEmail,
                        null,
                        null
                    )
                    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authToken
                }
            }
            filterChain.doFilter(request, response)
        } catch (ex: Exception) {
            ObjectMapper().writeValue(response.outputStream, ex.message)
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return !request.servletPath.contains("/toppings/personal")
    }
}