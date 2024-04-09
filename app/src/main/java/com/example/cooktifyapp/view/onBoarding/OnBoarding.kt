package com.example.cooktifyapp.view.onBoarding

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.ActivityOnBoardingBinding
import com.example.cooktifyapp.view.navigation.home.Home
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth

@Suppress("DEPRECATION")
class OnBoarding : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private val auth: FirebaseAuth = Firebase.auth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (isLoggedIn()) {
            redirectToMainActivity()
            return
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(applicationContext, gso)

        playAnimation()
        setupAction()
    }

    private fun isLoggedIn(): Boolean {
        return FirebaseAuth.getInstance().currentUser != null
    }

    private fun redirectToMainActivity() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(androidx.transition.R.anim.abc_fade_in,androidx.transition.R.anim.abc_fade_out)
    }

    private fun setupAction() {
        binding.btnLogin.setOnClickListener{
            val intent = Intent(this@OnBoarding, Login::class.java)
            startActivity(intent)
        }
        binding.btnRegis.setOnClickListener {
            val intent = Intent(this@OnBoarding, Register::class.java)
            startActivity(intent)
        }
        binding.ivGoogle.setOnClickListener {
            showLoading(true)
            signGoogle()
        }
    }

    private fun signGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d("OnBoarding", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w("OnBoarding", "Google sign in failed", e)
            }
        }else{
            showLoading(false)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    showLoading(false)
                    startActivity(Intent(this, Home::class.java))
                    finish()
                    Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT).show()
                } else {
                    showLoading(false)
                    Log.w("OnBoarding", "signInWithCredential:failure", task.exception)
                }
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

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }
}