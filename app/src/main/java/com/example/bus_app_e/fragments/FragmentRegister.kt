package com.example.bus_app_e.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.bus_app_e.R
import com.example.bus_app_e.databinding.FragmentRegBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.HashMap;

class FragmentRegister : Fragment() {
    private lateinit var binding: FragmentRegBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.RegisterBtn.setOnClickListener {
            if(binding.TextEmail.getText().toString().isEmpty() || binding.TextPass.getText().toString().isEmpty() || binding.TextUser.text.toString().isEmpty()){
                Toast.makeText(context, "Поля не могут быть пустыми!", Toast.LENGTH_SHORT).show();
            }else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.TextEmail.getText().toString(), binding.TextPass.getText().toString())
                    .addOnCompleteListener{task ->
                        if (task.isSuccessful){
                            val userInfo = HashMap<String, String>()
                            userInfo["email"] = binding.TextEmail.getText().toString()
                            userInfo["Password"] = binding.TextPass.getText().toString()
                            FirebaseAuth.getInstance().getCurrentUser()?.let { it1 ->
                                FirebaseDatabase.getInstance().getReference().child("Users").child(
                                    it1.getUid())
                                    .setValue(userInfo)
                            };
                        };
                            Toast.makeText(context, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                            it.findNavController().popBackStack();
                        }
                    }
            }
        binding.BtnBack.setOnClickListener{
            it.findNavController().popBackStack()
        }
        }
    }

