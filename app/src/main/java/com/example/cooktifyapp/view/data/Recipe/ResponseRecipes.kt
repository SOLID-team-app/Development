package com.example.cooktifyapp.view.data.Recipe

import android.os.Parcel
import android.os.Parcelable
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
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(caraMasak)
		parcel.writeString(namaMakanan)
		parcel.writeValue(loves)
		parcel.writeString(linkGambar)
		parcel.writeValue(jumlahBahanUtama)
		parcel.writeValue(id)
		parcel.writeString(bahanUtama)
		parcel.writeString(resep)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ResponseRecipesItem> {
		override fun createFromParcel(parcel: Parcel): ResponseRecipesItem {
			return ResponseRecipesItem(parcel)
		}

		override fun newArray(size: Int): Array<ResponseRecipesItem?> {
			return arrayOfNulls(size)
		}
	}
}

