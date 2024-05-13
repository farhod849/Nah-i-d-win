package com.example.bus_app_e.misc

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bus_app_e.databinding.TicketlayoutBinding
import java.util.Date

class TicketDiffCallback : DiffUtil.ItemCallback<Ticket>(){
    override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem.id==newItem.id
    }
    override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return  oldItem == newItem
    }
}

class TicketViewHolder(private val binding: TicketlayoutBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(ticket: Ticket) {
        binding.apply {
            NameTicket.text = ticket.name
            PriceTicket.text = ticket.price.toString()
            DateTicket.text = ticket.date
            BuyTicket.setOnClickListener{
                ticket.BuyedByMe = !ticket.BuyedByMe
                if (ticket.BuyedByMe)
            {
                BuyTicket.text = "Вернуть билет"
            }
            else
            {
                BuyTicket.text = "Купить билет"
            }
                Toast.makeText(binding.root.context, "Билет успешно куплен!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}


class TicketAdapter: ListAdapter<Ticket,TicketViewHolder>(TicketDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val binding = TicketlayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return TicketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = getItem(position)
        holder.bind(ticket)
    }
}