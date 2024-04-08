package com.example.cooktifyapp.view.onBoarding

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.cooktifyapp.databinding.ActivityRegisterBinding
import com.example.cooktifyapp.view.data.entity.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase

@Suppress("DEPRECATION")
class Register : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterBinding
    private val database = FirebaseDatabase.getInstance().getReference()
    private val auth: FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()
        setupAction()
    }

    private fun setupAction() {
        binding.ivLogo.setOnClickListener {
            val intent = Intent(this@Register, OnBoarding::class.java)
            startActivity(intent)
        }

        binding.btnRegis.setOnClickListener {
            showLoading(true)
            val nama = binding.etNama.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (nama.isEmpty()){
                binding.etNama.error = "Nama Tidak Boleh Kosong!!"
            }else if (email.isEmpty()){
                binding.etEmail.error = "Email Tidak Boleh Kosong!!"
            }else if (password.isEmpty()){
                binding.etPassword.error = "Password Tidak Boleh Kosong!!"
            }else{
                if (password.length > 6){
                    createAkun(nama, email, password)
                }else{
                    showLoading(false)
                    binding.tfPassword.error = "Password Harus Lebih Dari 6 Karakter!!"
                }

            }
        }
    }

    private fun createAkun(nama: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@Register){task ->
                if (task.isSuccessful){
                    auth.currentUser!!.sendEmailVerification()
                        .addOnCompleteListener { verifikasi ->
                            if (verifikasi.isSuccessful){
                                val user = auth.currentUser
                                val urlProfile = user?.photoUrl.toString()
                                val db = database.child("users")
                                val id: String? = user?.uid
                                val data = User (
                                    idUser = id,
                                    nama = nama,
                                    email = email,
                                    urlProfile = urlProfile,
                                )

                                db.child(id!!).setValue(data)
                                    .addOnSuccessListener {
                                        showLoading(false)
                                        Toast.makeText(applicationContext, "Registrasi berhasil. Silakan periksa email verifikasi Anda!!", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this, Login::class.java)
                                        startActivity(intent)
                                        overridePendingTransition(androidx.transition.R.anim.abc_fade_in,androidx.transition.R.anim.abc_fade_out)
                                    }
                                    .addOnFailureListener {
                                        showLoading(false)
                                    }
                            }else{
                                showLoading(false)
                                Toast.makeText(applicationContext, "Gagal mengirim verifikasi. Silakan coba lagi.", Toast.LENGTH_SHORT).show()
                            }
                        }
                }else{
                    showLoading(false)
                    Toast.makeText(applicationContext, "Registrasi gagal. Mungkin email Anda sudah digunakan.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun playAnimation() {

        ObjectAnimator.ofFloat(binding.ivLogo, View.TRANSLATION_X, -50f, 50f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val text = ObjectAnimator.ofFloat(binding.linearLayout, View.ALPHA, 1f).setDuration(800)
        val nama = ObjectAnimator.ofFloat(binding.tfNama, View.ALPHA, 1f).setDuration(800)
        val email = ObjectAnimator.ofFloat(binding.tfEmail, View.ALPHA, 1f).setDuration(800)
        val password = ObjectAnimator.ofFloat(binding.tfPassword, View.ALPHA, 1f).setDuration(800)
        val register = ObjectAnimator.ofFloat(binding.btnRegis, View.ALPHA, 1f).setDuration(800)

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

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }

}