package hu.bme.aut.movieapp.model

import com.google.gson.annotations.SerializedName

data class Genre (
    @SerializedName("name")
    var name: String? = null
)