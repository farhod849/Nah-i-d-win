package com.example.bus_app_e.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.bus_app_e.R
import com.example.bus_app_e.databinding.FragmentLoadingBinding
import com.example.bus_app_e.databinding.FragmentLoginBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Loading_Frag : Fragment() {
    private lateinit var binding: FragmentLoadingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            delay(5000)
            view.findNavController().navigate(R.id.action_loading_Frag_to_fragmentMainMenu)
        }
        with(binding){
            val anim: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.load_anim)
            ImageLoad.startAnimation(anim)
            Toast.makeText(context,"Успешный вход", Toast.LENGTH_SHORT).show();

        }
    }

}