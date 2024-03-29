package com.example.cooktifyapp.onBoarding

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cooktifyapp.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()
    }

    private fun playAnimation() {

        ObjectAnimator.ofFloat(binding.ivLogo, View.TRANSLATION_X, -50f, 50f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val text = ObjectAnimator.ofFloat(binding.linearLayout, View.ALPHA, 1f).setDuration(800)
        val nama = ObjectAnimator.ofFloat(binding.TextNama, View.ALPHA, 1f).setDuration(800)
        val email = ObjectAnimator.ofFloat(binding.TextEmail, View.ALPHA, 1f).setDuration(800)
        val password = ObjectAnimator.ofFloat(binding.TextPassword, View.ALPHA, 1f).setDuration(800)
        val register = ObjectAnimator.ofFloat(binding.buttonRegister, View.ALPHA, 1f).setDuration(800)

        AnimatorSet().apply {
            playSequentially(
                text,
                nama,
                email,
                password,
                register,
            )
            startDelay = 100
        }.start()
    }
}