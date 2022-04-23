package com.hamzaaktay.adbootcampconnecttotheinternet.network

import com.squareup.moshi.Json

data class CharacterResponse (
    @Json (name = "harrypotter") var characters : List<CharacterModel>,
    var success : Int
    )