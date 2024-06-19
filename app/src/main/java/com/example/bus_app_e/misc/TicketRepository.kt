package com.example.bus_app_e.misc

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class TicketRepository(val context: Context){
    private var dataClass = DataClass(
        emptyList(),
        User()
    )
    private val databaseUsersReference = FirebaseDatabase.getInstance().getReference("Users")
    private val uid:String = Firebase.auth.currentUser!!.uid
    private var data = MutableLiveData(dataClass)
    private var nextid:Long = 1
    fun sync(){
        data.value = dataClass
        MemoryManager(context).save(dataClass.listTicket)
    }

    fun setall(tickets1: List<Ticket>) {
        dataClass.listTicket = tickets1
        sync()
    }
    fun getall() = data
    fun addUser(user: User){
        databaseUsersReference.child(user.uid).removeValue()
        databaseUsersReference.child(user.uid).setValue(user)
    }
    fun addTicket(name: String, Price: Double, Data: String) {
        dataClass.listTicket = listOf(Ticket(nextid++,name,Price,Data, BuyedByMe = false)) + dataClass.listTicket
        sync()
    }

    fun loadUser() {
        val listener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.mapNotNull { it.getValue(User::class.java) }.forEach{
                    if(it.uid == uid)
                        dataClass.user = it
                }
                data.value = dataClass
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        }
        databaseUsersReference.addValueEventListener(listener)
    }

    fun getUser(): User {
        return dataClass.user
    }
}

class TicketViewModel(application: Application):AndroidViewModel(application)
{
    private val repository = TicketRepository(application)
    val data = repository.getall()
    fun addTicket(Name:String, Price:Double, Data:String) = repository.addTicket(Name,Price,Data)
    fun addUser(user:User) = repository.addUser(user)
    fun getUser() = repository.getUser()
    fun loadUser() = repository.loadUser()
    fun setall(tickets:List<Ticket>) = repository.setall(tickets)
}