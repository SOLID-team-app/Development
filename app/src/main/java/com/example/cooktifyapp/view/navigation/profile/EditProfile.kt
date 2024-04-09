@file:Suppress("DEPRECATION")

package com.example.cooktifyapp.view.navigation.profile

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.agilenesia.agilenesiaapps.ui.user.homepage.ui.profile.OnDialogCloseListener
import com.bumptech.glide.Glide
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.ActivityEditProfileBinding
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

class EditProfile : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "EditProfile"

        fun newInstance(): EditProfile {
            return EditProfile()
        }
    }

    private lateinit var binding: ActivityEditProfileBinding

    private val PERMISSION_CAMERA_REQUEST_CODE = 1
    private val PERMISSION_READ_EXTERNAL_STORAGE_REQUEST_CODE = 2

    private lateinit var storage: StorageReference
    private lateinit var uri: Uri

    private var urlProfile: String? = null

    private val database = FirebaseDatabase.getInstance().getReference()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storage = FirebaseStorage.getInstance().reference

        cekPermission()

        val idUser = auth.currentUser?.uid
        database.child("users").child(idUser!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val userName = snapshot.child("nama").getValue(String::class.java)
                        urlProfile = snapshot.child("urlProfile").getValue(String::class.java)

                        userName?.let { binding.etNama.setText(it) }
                        urlProfile?.let {
                            Glide.with(requireContext())
                                .load(it)
                                .placeholder(R.drawable.baseline_account_circle_24)
                                .into(binding.ivImage)
                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Data not found",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })

        setupAction()
    }

    private fun setupAction() {
        binding.cvFotoProfile.setOnClickListener {
            pilihGambar()
        }

        binding.btnSave.setOnClickListener {
            val idUser = auth.currentUser?.uid
            simpanData(idUser!!)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            20 -> {
                if (resultCode == RESULT_OK) {
                    handleImageResult(data)
                }
            }
        }
    }

    private fun handleImageResult(data: Intent?) {
        val path: Uri? = data?.data
        val thread = Thread {
            try {
                val stream: InputStream? = requireActivity().contentResolver.openInputStream(path!!)
                val bitmap = BitmapFactory.decodeStream(stream)
                binding.ivImage.post {
                    val idUser = auth.currentUser?.uid
                    binding.ivImage.setImageBitmap(bitmap)
                    uploadGambar(idUser!!)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    private fun cekPermission() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                PERMISSION_CAMERA_REQUEST_CODE
            )
        }

        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_READ_EXTERNAL_STORAGE_REQUEST_CODE
            )
        }
    }

    private fun pilihGambar() {
        val items = arrayOf<CharSequence>("Take From Gallery", "Cancel")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select Image")
        builder.setIcon(R.drawable.ic_mage)
        builder.setItems(items) { dialog, item ->
            when (items[item]) {
                "Take From Gallery" -> {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    startActivityForResult(Intent.createChooser(intent, "Select Image"), 20)
                }
                "Cancel" -> dialog.dismiss()
            }
        }
        builder.show()
    }

    private fun uploadGambar(idUser: String) {
        binding.ivImage.isDrawingCacheEnabled = true
        binding.ivImage.buildDrawingCache(true)
        val bitmapDrawable = binding.ivImage.drawable as BitmapDrawable
        val bitmap: Bitmap = bitmapDrawable.bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        val progressDialog = ProgressDialog(requireActivity())
        progressDialog.setTitle("Uploading...")
        progressDialog.show()

        val reference: StorageReference = storage.child("Profile/profile ($idUser).jpg")
        val uploadTask: UploadTask = reference.putBytes(data)
        uploadTask.addOnSuccessListener { taskSnapshot ->
            progressDialog.dismiss()
            val downloadUrlTask: Task<Uri> = taskSnapshot.storage.downloadUrl
            downloadUrlTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val dowloadUrl: Uri? = task.result
                    if (dowloadUrl != null) {
                        uri = dowloadUrl
                        urlProfile = uri.toString()
                    }
                } else {
                    Toast.makeText(requireContext(), "Upload Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }.addOnFailureListener {
            progressDialog.dismiss()
            Toast.makeText(requireContext(), "Upload Failed", Toast.LENGTH_SHORT).show()
        }.addOnProgressListener { snapshot ->
            val progress = (100.0 * snapshot.bytesTransferred) / snapshot.totalByteCount
            progressDialog.setMessage("Uploaded : " + progress.toInt() + "%")
        }
    }

    private fun simpanData(idUser: String) {
        val nama = binding.etNama.text?.toString()
        val update = HashMap<String, Any>()
        update["nama"] = nama!!
        update["urlProfile"] = urlProfile!!

        database.child("users").child(idUser).updateChildren(update)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Update Successful", Toast.LENGTH_SHORT).show()
                dismiss()
            }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val activity = requireActivity()
        if (activity is OnDialogCloseListener) {
            (activity as OnDialogCloseListener).onDialogClose(dialog)
        }
    }
}
