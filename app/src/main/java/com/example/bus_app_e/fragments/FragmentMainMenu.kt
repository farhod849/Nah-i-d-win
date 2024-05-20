package com.example.bus_app_e.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.bus_app_e.R
import com.example.bus_app_e.databinding.FragmentMainMenuBinding
import com.example.bus_app_e.misc.MemoryManager
import com.example.bus_app_e.misc.TicketAdapter
import com.example.bus_app_e.misc.TicketViewModel


class FragmentMainMenu : Fragment() {
    private lateinit var binding: FragmentMainMenuBinding
    private val ticketviewmodel: TicketViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainMenuBinding.inflate(layoutInflater, container, false)
        ticketviewmodel.setall(MemoryManager(binding.root.context).load())
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TicketAdapter()
        with(binding)
        {
         recyclertickets.adapter = adapter
         ticketviewmodel.data.observe(viewLifecycleOwner)
         {
             adapter.submitList(it)
         }
          binding.addticketbtn.setOnClickListener{
              it.findNavController().navigate(R.id.action_fragmentMainMenu_to_fragmentCreateTicket)
          }
            binding.ProfileBTN.setOnClickListener{
                it.findNavController().navigate(R.id.action_fragmentMainMenu_to_fragmentProfile)
            }
            binding.MapBtn.setOnClickListener {
                it.findNavController().navigate(R.id.action_fragmentMainMenu_to_map_fragment)
            }
        }
    }
}