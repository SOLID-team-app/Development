package com.example.cooktifyapp.view.navigation.inputImages

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.FragmentInputImageBinding
import com.example.cooktifyapp.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

@Suppress("DEPRECATION")
class InputImage : Fragment() {

    private lateinit var binding:FragmentInputImageBinding

    private val PERMISSION_CAMERA_REQUEST_CODE = 1
    private val PERMISSION_READ_EXTERNAL_STORAGE_REQUEST_CODE = 1
    private lateinit var labels: List<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputImageBinding.inflate(inflater, container, false)
        val root = binding.root

        labels = requireContext().assets.open("labels.txt").bufferedReader().readLines()

        cekPermission()
        setupAction()

        return root
    }


    private fun setupAction() {
        binding.cv1.setOnClickListener {
            pilihGambarBahan1()
        }

        binding.cv2.setOnClickListener {
            pilihGambarBahan2()
        }

        binding.cv3.setOnClickListener {
            pilihGambarBahan3()
        }

        binding.btnStart.setOnClickListener {
//            val bitmap1 = (binding.ivImage1.drawable as BitmapDrawable).bitmap
//            val bitmap2 = (binding.ivImage2.drawable as BitmapDrawable).bitmap
//            val bitmap3 = (binding.ivImage3.drawable as BitmapDrawable).bitmap
//
//            processImage(binding.ivImage1, binding.tvNamaBahan1, bitmap1)
//            processImage(binding.ivImage2, binding.tvNamaBahan2, bitmap2)
//            processImage(binding.ivImage3, binding.tvNamaBahan3, bitmap3)
        }
    }

    private fun pilihGambarBahan1() {
        val items = arrayOf<CharSequence>("Ambil Foto", "Ambil Dari Galeri", "Cancel")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Pilih Gambar")
        builder.setIcon(R.drawable.tulisan_cooktify)
        builder.setItems(items) { dialog, item ->
            when {
                items[item] == "Ambil Foto" -> {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(intent, 30)
                }
                items[item] == "Ambil Dari Galeri" -> {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 40)
                }
                items[item] == "Cancel" -> dialog.dismiss()
            }
        }
        builder.show()
    }

    private fun pilihGambarBahan2() {
        val items = arrayOf<CharSequence>("Ambil Foto", "Ambil Dari Galeri", "Cancel")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Pilih Gambar")
        builder.setIcon(R.drawable.tulisan_cooktify)
        builder.setItems(items) { dialog, item ->
            when {
                items[item] == "Ambil Foto" -> {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(intent, 50)
                }
                items[item] == "Ambil Dari Galeri" -> {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 60)
                }
                items[item] == "Cancel" -> dialog.dismiss()
            }
        }
        builder.show()
    }

    private fun pilihGambarBahan3() {
        val items = arrayOf<CharSequence>("Ambil Foto", "Ambil Dari Galeri", "Cancel")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Pilih Gambar")
        builder.setIcon(R.drawable.tulisan_cooktify)
        builder.setItems(items) { dialog, item ->
            when {
                items[item] == "Ambil Foto" -> {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(intent, 70)
                }
                items[item] == "Ambil Dari Galeri" -> {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 80)
                }
                items[item] == "Cancel" -> dialog.dismiss()
            }
        }
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 40 && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            val path: Uri? = data.data
            val thread = Thread {
                try {
                    val stream: InputStream? = requireActivity().contentResolver.openInputStream(path!!)
                    val bitmap = BitmapFactory.decodeStream(stream)
                    binding.ivImage1.post {
                        binding.ivImage1.setImageBitmap(bitmap)
                        bitmap?.let { processImage(binding.ivImage1, binding.tvNamaBahan1, it) }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            thread.start()
        }

        if (requestCode == 30 && resultCode == AppCompatActivity.RESULT_OK) {
            val extras: Bundle? = data?.extras
            val thread = Thread {
                val bitmap = extras?.get("data") as Bitmap?
                binding.ivImage1.post {
                    binding.ivImage1.setImageBitmap(bitmap)
                    bitmap?.let { processImage(binding.ivImage1, binding.tvNamaBahan1, it) }
                }
            }
            thread.start()
        }

        if (requestCode == 60 && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            val path: Uri? = data.data
            val thread = Thread {
                try {
                    val stream: InputStream? = requireActivity().contentResolver.openInputStream(path!!)
                    val bitmap = BitmapFactory.decodeStream(stream)
                    binding.ivImage2.post {
                        binding.ivImage2.setImageBitmap(bitmap)
                        bitmap?.let { processImage(binding.ivImage2, binding.tvNamaBahan2, it) }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            thread.start()
        }

        if (requestCode == 50 && resultCode == AppCompatActivity.RESULT_OK) {
            val extras: Bundle? = data?.extras
            val thread = Thread {
                val bitmap = extras?.get("data") as Bitmap?
                binding.ivImage2.post {
                    binding.ivImage2.setImageBitmap(bitmap)
                    bitmap?.let { processImage(binding.ivImage2, binding.tvNamaBahan2, it) }
                }
            }
            thread.start()
        }

        if (requestCode == 80 && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            val path: Uri? = data.data
            val thread = Thread {
                try {
                    val stream: InputStream? = requireActivity().contentResolver.openInputStream(path!!)
                    val bitmap = BitmapFactory.decodeStream(stream)
                    binding.ivImage3.post {
                        binding.ivImage3.setImageBitmap(bitmap)
                        bitmap?.let { processImage(binding.ivImage3, binding.tvNamaBahan3, it) }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            thread.start()
        }

        if (requestCode == 70 && resultCode == AppCompatActivity.RESULT_OK) {
            val extras: Bundle? = data?.extras
            val thread = Thread {
                val bitmap = extras?.get("data") as Bitmap?
                binding.ivImage3.post {
                    binding.ivImage3.setImageBitmap(bitmap)
                    bitmap?.let { processImage(binding.ivImage3, binding.tvNamaBahan3, it) }
                }
            }
            thread.start()
        }
    }

    private fun cekPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
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
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                PERMISSION_READ_EXTERNAL_STORAGE_REQUEST_CODE
            )
        }
    }

    private fun processImage(imageView: ImageView, textView: TextView, image: Bitmap) {
        try {
            val model = Model.newInstance(requireContext())

            val imageSize = 300

            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, imageSize, imageSize, 3), DataType.FLOAT32)

            val intValues = IntArray(image.width * image.height)
            image.getPixels(intValues, 0, image.width, 0, 0, image.width, image.height)

            val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
            byteBuffer.order(ByteOrder.nativeOrder())

            var pixel = 0
            for (i in 0 until imageSize) {
                for (j in 0 until imageSize) {
                    if (pixel < intValues.size) {
                        val value = intValues[pixel++]
                        byteBuffer.putFloat(((value shr 16) and 0xFF) * (1f / 255f))
                        byteBuffer.putFloat(((value shr 8) and 0xFF) * (1f / 255f))
                        byteBuffer.putFloat((value and 0xFF) * (1f / 255f))
                    }
                }
            }

            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            val confidences: FloatArray = outputFeature0.floatArray

            for (i in confidences.indices) {
                Log.d("CameraActivity", "Confidence for class $i: ${confidences[i]}")
            }

            var maxPos = 0
            var maxConfidence = 0.0f

            for (i in confidences.indices) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i]
                    maxPos = i
                }
            }

            val classes = arrayOf(
                "Alpukat",
                "Anggur",
                "Apel",
                "Asparagus",
                "Brokoli",
                "Buah naga",
                "Ceker ayam",
                "Dada ayam",
                "Durian",
                "Jagung",
                "Jambu air",
                "Jeruk",
                "Kacang mete",
                "Kacang polong",
                "Kacang tanah",
                "Kangkung",
                "Kelapa",
                "Kembang kol",
                "Kentang",
                "Klengkeng",
                "Labu",
                "Labu siam",
                "Leci",
                "Lemon",
                "Lobak merah",
                "Mangga",
                "Melon",
                "Mentimun",
                "Nanas",
                "Nangka",
                "Nasi merah",
                "Nasi putih",
                "Paha bawah ayam",
                "Paprika",
                "Pare",
                "Pepaya",
                "Pir",
                "Pisang",
                "Pokcoy",
                "Pork belly",
                "Rambutan",
                "Salmon",
                "Sayap ayam",
                "Semangka",
                "Singkong",
                "Stroberi",
                "Tahu",
                "Tauge",
                "Telur",
                "Tempe",
                "Terong",
                "Tomat",
                "Ubi",
                "Wortel"
            )

            val detectedClass = classes[maxPos]
            imageView.post {
                textView.text = detectedClass
            }

            model.close()

        } catch (e: Exception) {
            Log.e("CameraActivity", "Error processing image", e)
        }

    }

}

