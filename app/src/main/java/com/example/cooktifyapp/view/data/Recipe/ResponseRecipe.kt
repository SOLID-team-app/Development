package com.example.cooktifyapp.view.data.Recipe

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class ResponseRecipe (

	@field:SerializedName("listMakanan")
	val listMakanan: List<ListMakanan>? = emptyList(),

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
@Parcelize
data class ListMakanan(

	@field:SerializedName("cara_masak")
	val caraMasak: String? = null,

	@field:SerializedName("nama_makanan")
	val namaMakanan: String? = null,

	@field:SerializedName("loves")
	val loves: Int? = null,

	@field:SerializedName("link_gambar")
	val linkGambar: String? = null,

	@field:SerializedName("jumlah_bahan_utama")
	val jumlahBahanUtama: Int? = null,

	@field:SerializedName("id")
	var id: Int? = null,

	@field:SerializedName("bahan_utama")
	val bahanUtama: String? = null,

	@field:SerializedName("resep")
	val resep: String? = null
): Parcelable
