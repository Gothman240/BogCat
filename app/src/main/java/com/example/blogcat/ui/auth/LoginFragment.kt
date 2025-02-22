package com.example.blogcat.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.blogcat.R
import com.example.blogcat.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
    }
    
    private fun isUserLoggedIn(){
        firebaseAuth.currentUser?.let { 
            findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
        }
    }
    
    private fun doLogin() {
        binding.btnSignin.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            validateCredentials(email, password)
            signIn(email, password)
        }
    }

    private fun signIn(email: String, password: String) {

    }

    private fun validateCredentials(email: String, password: String) {
        if (email.isEmpty()){
            binding.editTextEmail.error = "E-mail is empty"
            return
        }
        if (password.isEmpty()){
            binding.editTextPassword.error = "Password is empty"
            return
        }
    }

}