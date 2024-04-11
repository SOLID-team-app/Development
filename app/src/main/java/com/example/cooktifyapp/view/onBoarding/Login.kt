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
import com.example.cooktifyapp.databinding.ActivityLoginBinding
import com.example.cooktifyapp.view.navigation.home.Home
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth

class Login : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private val auth: FirebaseAuth = Firebase.auth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(applicationContext, gso)

        playAnimation()
        setupAction()
    }

    private fun setupAction() {
        binding.ivLogo.setOnClickListener {
            val intent = Intent(this@Login, OnBoarding::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            showLoading(true)
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (!(email.isEmpty() || password.isEmpty())) {
                loginDenganEmail(email, password)
            } else {
                showLoading(false)
                Toast.makeText(applicationContext, "Data Tidak Terdaftar", Toast.LENGTH_SHORT).show()
            }

        }

        binding.ivGoogle.setOnClickListener {
            showLoading(true)
            signGoogle()
        }
    }

    private fun loginDenganEmail(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (auth.currentUser!!.isEmailVerified) {
                    showLoading(false)
                    Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext,Home::class.java))
                    finish()
                } else {
                    showLoading(false)
                    binding.tfEmail.error = "Silahkan Cek Email Dan Verifikasi Akun Anda"
                }
            } else {
                showLoading(false)
                Toast.makeText(applicationContext, "Login Anda Gagal", Toast.LENGTH_SHORT).show()
            }
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
                Log.d("Profile Fragment", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w("Profile Fragment", "Google sign in failed", e)
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
                    Log.w("Profile Fragment", "signInWithCredential:failure", task.exception)
                }
            }
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

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }
}