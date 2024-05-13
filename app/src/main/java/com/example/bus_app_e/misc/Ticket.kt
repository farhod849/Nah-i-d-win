package com.example.bus_app_e.misc

data class Ticket(
    val id: Long,
    val name: String,
    val price: Double,
    val date: String,
    var BuyedByMe: Boolean
)
