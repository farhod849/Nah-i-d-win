package com.example.bus_app_e.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.bus_app_e.R
import com.example.bus_app_e.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class FragmentLogin : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.QuitBtn.setOnClickListener{
            System.exit(0);
        }
        binding.LoginBtn.setOnClickListener {
            if(binding.TextEmail.getText().toString().isEmpty() || binding.TextPassword.getText().toString().isEmpty()){
                Toast.makeText(context, "Поля не могут быть пустыми!", Toast.LENGTH_SHORT).show();
            }else{

                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.TextEmail.getText().toString(), binding.TextPassword.getText().toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            it.findNavController().navigate(R.id.action_fragmentLogin_to_fragmentMainMenu)
                            Toast.makeText(context,"Успешный вход", Toast.LENGTH_SHORT).show();
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
