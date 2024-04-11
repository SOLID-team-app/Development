package com.example.cooktifyapp.view.settings

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.ActivitySettingsBinding
import com.example.cooktifyapp.view.onBoarding.Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private val auth: FirebaseAuth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, Login::class.java))
            this.finish()
            this.overridePendingTransition(androidx.transition.R.anim.abc_fade_in,androidx.transition.R.anim.abc_fade_out)
        }

    }
}