package com.github.mrf1n.pizzeria.model

import jakarta.persistence.*

@Entity
class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    lateinit var name: String

    companion object {
        fun of(name: String): Topping =
            Topping().also { it.name = name }
    }
}
