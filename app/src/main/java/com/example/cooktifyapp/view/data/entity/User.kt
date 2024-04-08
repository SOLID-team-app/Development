package com.example.cooktifyapp.view.data.entity

import android.os.Parcel
import android.os.Parcelable

data class User(
    var idUser: String?,
    var nama: String?,
    var email: String?,
    var urlProfile: String?,
) : Parcelable {

    constructor() : this(null, null, null, null)

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idUser)
        parcel.writeString(nama)
        parcel.writeString(email)
        parcel.writeString(urlProfile)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
