package com.hamzaaktay.adbootcampconnecttotheinternet.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize // --> Verilerin hepsini tek seferde detaila göndermek için
data class CharacterModel(
    val id: Int,
    val name:String,
    @Json(name = "image_url") val imageUrl: String,
    val house: String
): Parcelable // --> Verilerin hepsini tek seferde detaila göndermek için
