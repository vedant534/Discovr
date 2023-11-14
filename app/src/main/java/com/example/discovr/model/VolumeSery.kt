package com.example.discovr.model


import com.google.gson.annotations.SerializedName

data class VolumeSery(
    @SerializedName("orderNumber")
    val orderNumber: Int,
    @SerializedName("seriesBookType")
    val seriesBookType: String,
    @SerializedName("seriesId")
    val seriesId: String
)