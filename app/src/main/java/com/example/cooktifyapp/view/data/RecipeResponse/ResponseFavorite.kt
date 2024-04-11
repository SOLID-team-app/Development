package com.example.cooktifyapp.view.data.RecipeResponse

data class ResponseFavorite(
	val responseFavorite: List<ResponseFavoriteItem?>? = null
)

data class ResponseFavoriteItem(
	val caraMasak: String? = null,
	val namaMakanan: String? = null,
	val loves: Int? = null,
	val linkGambar: String? = null,
	val jumlahBahanUtama: Int? = null,
	val id: Int? = null,
	val bahanUtama: String? = null,
	val resep: String? = null
)

