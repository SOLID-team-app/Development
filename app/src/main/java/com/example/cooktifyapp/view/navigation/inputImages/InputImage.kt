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


    companion object {
        private const val TAG = "InputImageFragment"
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
            startCameraCapture(200)
            startCameraCapture(201)
            startCameraCapture(202)
        }


        binding.btnStart.setOnClickListener {


        }

        return view
    }

    private fun startCameraCapture(requestCode: Int) {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, requestCode)
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
            200,201,202 -> {
                handleCameraResult(requestCode, resultCode, data)
            }
        }
    }

    private fun handleCameraResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            val imageBitmap = data.extras?.get("data") as? Bitmap
            if (imageBitmap != null) {
                when (requestCode) {
                    200 -> binding.itemsImg.setImageBitmap(imageBitmap)
                    201 -> binding.itemsImg2.setImageBitmap(imageBitmap)
                    202 -> binding.itemsImg3.setImageBitmap(imageBitmap)
                }
            } else {
                Log.e(TAG, "Bitmap is null")
            }
        } else {
            Log.e(TAG, "Camera result is not OK or data is null")
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

