package com.example.discovr.model


import com.google.gson.annotations.SerializedName

data class SeriesInfo(
    @SerializedName("bookDisplayNumber")
    val bookDisplayNumber: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("shortSeriesBookTitle")
    val shortSeriesBookTitle: String,
    @SerializedName("volumeSeries")
    val volumeSeries: List<VolumeSery>
)