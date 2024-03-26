package com.example.cooktifyapp.onBoarding

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
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
    }

    private fun playAnimation() {

        ObjectAnimator.ofFloat(binding.ivGambar, View.TRANSLATION_X, -50f, 50f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val text = ObjectAnimator.ofFloat(binding.tvDesc, View.ALPHA, 1f).setDuration(800)
        val layout = ObjectAnimator.ofFloat(binding.llButton, View.ALPHA, 1f).setDuration(800)
        val tvAtau = ObjectAnimator.ofFloat(binding.tvAtau, View.ALPHA, 1f).setDuration(800)
        val logo = ObjectAnimator.ofFloat(binding.logoGoogle, View.ALPHA, 1f).setDuration(800)

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