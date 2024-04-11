package com.example.cooktifyapp.view.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cooktifyapp.databinding.FragmentHomeBinding
import com.example.cooktifyapp.view.Recipe.Recipe
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {
    
    private lateinit var binding: FragmentHomeBinding
    private val database = FirebaseDatabase.getInstance().getReference()
    private val auth: FirebaseAuth = Firebase.auth
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        getNamaUser()
        setupAction()
        
        return binding.root
    }

    private fun setupAction() {
        binding.ivSeeAll.setOnClickListener{
            val intent = Intent(requireContext(), Recipe::class.java)
            startActivity(intent)
        }
    }

    private fun getNamaUser() {
        val idUser = auth.currentUser?.uid
        database.child("users").child(idUser!!)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val userName = snapshot.child("nama").getValue(String::class.java)

                        userName?.let { it -> "Hi, $it".also { binding.tvUser.text = it } }

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


}