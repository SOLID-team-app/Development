package com.example.cooktifyapp.view.data.Recipe

data class ResponseRecipes(
	@SerializedName("response_Recipes")
	val responseRecipes: List<ResponseRecipesItem?>? = emptyList()
)

data class ResponseRecipesItem(
	@SerializedName("cara_masak")
	val caraMasak: String? = null,

	@SerializedName("nama_makanan")
	val namaMakanan: String? = null,

	@SerializedName("loves")
	val loves: Int? = null,

	@SerializedName("link_gambar")
	val linkGambar: String? = null,

	@SerializedName("jumlah_bahan_utama")
	val jumlahBahanUtama: Int? = null,

	@SerializedName("id")
	var id: Int? = null,

	@SerializedName("bahan_utama")
	val bahanUtama: String? = null,

	@SerializedName("resep")
	val resep: String? = null
)

annotation class SerializedName(val value: String)
