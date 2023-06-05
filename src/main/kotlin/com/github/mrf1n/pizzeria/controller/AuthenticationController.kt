package com.github.mrf1n.pizzeria.controller

import com.github.mrf1n.pizzeria.model.request.AuthenticationRequest
import com.github.mrf1n.pizzeria.model.response.AuthenticationResponse
import com.github.mrf1n.pizzeria.service.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthenticationController(val service: AuthenticationService) {


    @PostMapping("/signin")
    fun singIn(@RequestBody request: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(service.authentication(request))
    }

}