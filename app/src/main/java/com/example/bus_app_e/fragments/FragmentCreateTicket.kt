package com.example.bus_app_e.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.bus_app_e.databinding.FragmentCreateTicketBinding
import com.example.bus_app_e.misc.TicketViewModel


class FragmentCreateTicket : Fragment() {
    private lateinit var binding: FragmentCreateTicketBinding
    private val ticketviewmodel: TicketViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTicketBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.AddTicket.setOnClickListener{
            ticketviewmodel.addTicket(binding.NameTicket.text.toString(),binding.PriceTicket.text.toString().toDouble(),binding.DateTicket.text.toString())
            binding.NameTicket.setText("")
            binding.PriceTicket.setText("")
            binding.DateTicket.setText("")
            Toast.makeText(binding.root.context, "Рейс добавлен в список!", Toast.LENGTH_SHORT).show()
            it.findNavController().popBackStack()
        }
    }
}