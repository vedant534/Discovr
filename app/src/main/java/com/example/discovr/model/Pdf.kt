package com.example.discovr.model


import com.google.gson.annotations.SerializedName

data class Pdf(
    @SerializedName("acsTokenLink")
    val acsTokenLink: String?,
    @SerializedName("isAvailable")
    val isAvailable: Boolean
)