package com.example.cooktifyapp.view.onBoarding

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cooktifyapp.databinding.ActivityOnBoardingBinding

class OnBoarding : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()


        binding.btnLogin.setOnClickListener{
            val intent = Intent(this@OnBoarding, Login::class.java)
            startActivity(intent)
        }
        binding.btnRegis.setOnClickListener {
            val intent = Intent(this@OnBoarding, Register::class.java)
            startActivity(intent)
        }
    }

    private fun playAnimation() {

        ObjectAnimator.ofFloat(binding.ivLogo, View.TRANSLATION_X, -50f, 50f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val text = ObjectAnimator.ofFloat(binding.tvDesc, View.ALPHA, 1f).setDuration(800)
        val layout = ObjectAnimator.ofFloat(binding.llButton, View.ALPHA, 1f).setDuration(800)
        val tvAtau = ObjectAnimator.ofFloat(binding.tvAtau, View.ALPHA, 1f).setDuration(800)
        val logo = ObjectAnimator.ofFloat(binding.ivGoogle, View.ALPHA, 1f).setDuration(800)

        AnimatorSet().apply {
            playSequentially(
                text,
                layout,
                tvAtau,
                logo
            )
            startDelay = 100
        }.start()
    }
}