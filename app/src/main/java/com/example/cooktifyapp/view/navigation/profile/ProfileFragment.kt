package com.example.cooktifyapp.view.navigation.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cooktifyapp.databinding.FragmentProfileBinding
import com.example.cooktifyapp.view.onBoarding.Login
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Suppress("DEPRECATION")
class ProfileFragment : Fragment() {

    private lateinit var binding:FragmentProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        auth = Firebase.auth
        setupAction()

        return binding.root
    }

    private fun setupAction() {
        binding.tvKeluar.setOnClickListener {
            auth.signOut()
            startActivity(Intent(requireActivity(), Login::class.java))
            requireActivity().finish()
            requireActivity().overridePendingTransition(androidx.transition.R.anim.abc_fade_in,androidx.transition.R.anim.abc_fade_out)
        }
    }


}