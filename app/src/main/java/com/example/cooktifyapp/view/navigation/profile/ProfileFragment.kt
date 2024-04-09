package com.example.cooktifyapp.view.navigation.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.FragmentProfileBinding
import com.example.cooktifyapp.view.onBoarding.Login
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Suppress("DEPRECATION")
class ProfileFragment : Fragment() {

    private lateinit var binding:FragmentProfileBinding
    private val auth: FirebaseAuth = Firebase.auth
    private val database = FirebaseDatabase.getInstance().getReference()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        setupProfile()
        setupAction()

        return binding.root
    }

    private fun setupProfile() {
        val idUser = auth.currentUser?.uid
        database.child("users").child(idUser!!)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val userName = snapshot.child("nama").getValue(String::class.java)
                        val urlProfile = snapshot.child("urlProfile").getValue(String::class.java)
                        val email = snapshot.child("email").getValue(String::class.java)

                        userName?.let { binding.tvNamaUser.text = it }
                        email?.let { binding.tvEmailUser.text = it }
                        urlProfile?.let {
                            Glide.with(requireActivity())
                                .load(it)
                                .placeholder(R.drawable.baseline_account_circle_24)
                                .into(binding.ivPhotoUser)
                        }

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Data not found",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle onCancelled
                }
            })
    }

    private fun setupAction() {
        binding.tvKeluar.setOnClickListener {
            auth.signOut()
            startActivity(Intent(requireActivity(), Login::class.java))
            requireActivity().finish()
            requireActivity().overridePendingTransition(androidx.transition.R.anim.abc_fade_in,androidx.transition.R.anim.abc_fade_out)
        }

        binding.tvInfoPribadi.setOnClickListener {
            val editProfile = EditProfile.newInstance()
            editProfile.show(childFragmentManager, EditProfile.TAG)
        }
    }


}