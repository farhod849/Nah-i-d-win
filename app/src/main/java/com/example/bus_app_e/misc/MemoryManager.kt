package com.example.bus_app_e.misc

import android.content.Context
import com.google.gson.Gson

class MemoryManager(context: Context) {
    private val prefer =  context.getSharedPreferences("InfoTickets", Context.MODE_PRIVATE)
    private val editor = prefer.edit()
    fun save(tickets: List<Ticket>)
    {
        var count = 0
        tickets.forEach {
            editor.putString("ticket${count++}" ,Gson().toJson(it))
        }
        editor.putInt("count", count)
        editor.apply()
    }
    fun load(): List<Ticket>{
        var tickets = listOf<Ticket>()
        var count = prefer.getInt("count", 0)
        for (n in 0 .. count - 1) {
            tickets = tickets + listOf( Gson().fromJson(prefer.getString("ticket${n}", ""), Ticket::class.java))
        }
        return tickets
    }
}