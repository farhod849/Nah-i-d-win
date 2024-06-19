package com.example.bus_app_e.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.bus_app_e.R
import com.example.bus_app_e.databinding.FragmentLoginBinding
import com.example.bus_app_e.misc.TicketViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlin.system.exitProcess


class FragmentLogin : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel:TicketViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.QuitBtn.setOnClickListener{
            exitProcess(0)
        }
        binding.LoginBtn.setOnClickListener {
            if(binding.TextEmail.text.toString().isEmpty() || binding.TextPassword.text.toString().isEmpty()){
                Toast.makeText(context, "Поля не могут быть пустыми!", Toast.LENGTH_SHORT).show();
            }else{
                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.TextEmail.text.toString(), binding.TextPassword.getText().toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            viewModel.loadUser()
                            it.findNavController().navigate(R.id.action_fragmentLogin_to_loading_Frag)
                        }
                        else
                        {
                            Toast.makeText(context,"Ошибка входа, неверный пароль/логин", Toast.LENGTH_SHORT).show();
                        }
                    };
            }
        }
        binding.GoToRegActivity.setOnClickListener{
            it.findNavController().navigate(R.id.action_fragmentLogin_to_fragmentRegister)
        }
    }


}
