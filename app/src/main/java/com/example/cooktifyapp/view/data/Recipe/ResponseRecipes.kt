package com.example.cooktifyapp.view.data.Recipe

import com.google.gson.annotations.SerializedName

data class ResponseRecipes(
	@field:SerializedName("response_Recipes")
	val responseRecipes: List<ResponseRecipesItem?>? = emptyList()
)

data class ResponseRecipesItem(
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
)

