package com.kultur.futbolfiksturuygulamasi.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataModel {
    @SerializedName("takim")
    @Expose
    var takim: String? = null
}