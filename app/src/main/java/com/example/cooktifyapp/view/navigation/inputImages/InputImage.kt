package com.example.cooktifyapp.view.navigation.inputImages

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.FragmentInputImageBinding
import com.example.cooktifyapp.view.Utils.Utils.getImageUri

class InputImage : Fragment() {

    private  var _binding: FragmentInputImageBinding? = null
    private val binding get() = _binding!!
    private var currentImageUri: Uri? = null
    private fun allPermissionGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputImageBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.cardGalery.setOnClickListener {
            startImageSelection(100)
            startImageSelection(101)
            startImageSelection(102)
        }
        binding.cardCamera.setOnClickListener {
            startCamera()
        }


        binding.btnStart.setOnClickListener {


        }


        return view
    }

    private fun startCamera() {
        if (allPermissionGranted()) {
            currentImageUri = getImageUri(requireContext())
            launcherIntentCamera.launch(currentImageUri)
        }
    }


    private fun startImageSelection(requestCode: Int) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            100, 101, 102 -> {
                handleImageResult(requestCode, data)
            }

        }
    }


    private fun handleImageResult(requestCode: Int, data: Intent?) {
        if (data != null) {
            val uri = data.data
            if (uri != null) {
                val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
                when (requestCode) {
                    100 -> binding.itemsImg.setImageBitmap(bitmap)
                    101 -> binding.itemsImg2.setImageBitmap(bitmap)
                    102 -> binding.itemsImg3.setImageBitmap(bitmap)
                }
            }
        }
    }


    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            val itemsImg = binding.itemsImg
            val itemsImg2 = binding.itemsImg2
            val itemsImg3 = binding.itemsImg3
            showImage(itemsImg, itemsImg2 , itemsImg3 )
        }
    }
    private fun showImage(itemsImg: ImageView, itemsImg2: ImageView, itemsImg3: ImageView) {
        currentImageUri?.let {uri ->
            binding.itemsImg.setImageURI(uri)
            binding.itemsImg2.setImageURI(uri)
            binding.itemsImg3.setImageURI(uri)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

