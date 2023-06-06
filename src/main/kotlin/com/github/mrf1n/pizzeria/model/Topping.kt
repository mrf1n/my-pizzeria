package com.github.mrf1n.pizzeria.model

import jakarta.persistence.*

@Entity
data class Topping(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true)
    var name: String
)
