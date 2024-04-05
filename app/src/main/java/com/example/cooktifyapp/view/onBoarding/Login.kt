package com.example.cooktifyapp.view.onBoarding

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()
    }

    private fun playAnimation() {

        ObjectAnimator.ofFloat(binding.ivLogo, View.TRANSLATION_X, -50f, 50f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val text = ObjectAnimator.ofFloat(binding.loginLayout, View.ALPHA, 1f).setDuration(800)
        val username = ObjectAnimator.ofFloat(binding.tfEmail, View.ALPHA, 1f).setDuration(800)
        val password = ObjectAnimator.ofFloat(binding.tfPassword, View.ALPHA, 1f).setDuration(800)
        val login = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(800)
        val tvAtau = ObjectAnimator.ofFloat(binding.tvAtau, View.ALPHA, 1f).setDuration(800)
        val ivGoogle = ObjectAnimator.ofFloat(binding.ivGoogle, View.ALPHA, 1f).setDuration(800)

        AnimatorSet().apply {
            playSequentially(
                text,
                username,
                password,
                login,
                tvAtau,
                ivGoogle
            )
            startDelay = 100
        }.start()
    }
}