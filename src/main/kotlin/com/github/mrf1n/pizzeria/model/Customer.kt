package com.github.mrf1n.pizzeria.model

import jakarta.persistence.*

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true)
    var email: String
) {

    @ManyToMany
    @JoinTable(
        name = "customer_topping",
        joinColumns = [JoinColumn(name = "customer_id")],
        inverseJoinColumns = [JoinColumn(name = "topping_id")]
    )
    val toppings: MutableSet<Topping> = mutableSetOf()
}