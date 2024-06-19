package com.example.bus_app_e.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bus_app_e.R
import com.example.bus_app_e.databinding.FragmentLoginBinding
import com.example.bus_app_e.databinding.FragmentMapFragmentBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView

class map_fragment : Fragment() {
    private lateinit var binding: FragmentMapFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
            MapKitFactory.initialize(context)
        binding = FragmentMapFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MapKitFactory.getInstance().onStart()
        binding.mapview.onStart()
    }
    override fun onStop() {
        binding.mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}