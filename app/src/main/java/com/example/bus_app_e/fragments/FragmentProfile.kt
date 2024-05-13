package com.example.bus_app_e.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.bus_app_e.R
import com.example.bus_app_e.databinding.FragmentMainMenuBinding
import com.example.bus_app_e.databinding.FragmentProfileBinding
import com.example.bus_app_e.misc.TicketAdapter
import com.example.bus_app_e.misc.TicketViewModel


class FragmentProfile : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val ticketviewmodel: TicketViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TicketAdapter()
        binding.recycler1.adapter = adapter
        ticketviewmodel.data.observe(viewLifecycleOwner, {
            adapter.submitList(it.filter {it.BuyedByMe})
        })

    }
}



