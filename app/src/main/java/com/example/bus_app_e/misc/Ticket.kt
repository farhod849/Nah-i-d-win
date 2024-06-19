package com.example.bus_app_e.misc

data class DataClass(
    var listTicket: List<Ticket> = emptyList<Ticket>(),
    var user: User = User()
    )
data class Ticket(
    val id: Long,
    val name: String,
    val price: Double,
    val date: String,
    var BuyedByMe: Boolean
)
data class User(
    val uid: String = "",
    val email:String = "",
    val password:String = "",
    var isAdmin: Boolean = false
)
