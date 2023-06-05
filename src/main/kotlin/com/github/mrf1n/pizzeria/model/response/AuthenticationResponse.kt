package com.github.mrf1n.pizzeria.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthenticationResponse(
    @JsonProperty("access_token")
    val accessToken: String
)