package com.example.bus_app_e.misc

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface Funcs
{
    fun setall(tickets:List<Ticket>)
    fun getall(): LiveData<List<Ticket>>
    fun add(Name:String,Price:Double,Data:String)
}
class TicketRepository(val context: Context):Funcs{
    private var tickets:List<Ticket> = emptyList()
    private var data = MutableLiveData(tickets)
    private var nextid:Long = 1
    fun sync()
    {
        data.value = tickets
        MemoryManager(context).save(tickets)
    }

    override fun setall(tickets1: List<Ticket>) {
        tickets = tickets1
        sync()
    }
    override fun getall(): LiveData<List<Ticket>> {
       return data
    }
    override fun add(Name: String, Price: Double, Data: String) {
       tickets = listOf(Ticket(nextid++,Name,Price,Data, BuyedByMe = false)) + tickets
        sync()
    }
}

class TicketViewModel(application: Application):AndroidViewModel(application)
{
    private val repository = TicketRepository(application)
    val data = repository.getall()
    fun add(Name:String,Price:Double,Data:String) = repository.add(Name,Price,Data)
    fun setall(tickets:List<Ticket>) = repository.setall(tickets)
}