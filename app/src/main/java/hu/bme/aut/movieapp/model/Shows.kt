package hu.bme.aut.movieapp.model

import com.google.gson.annotations.SerializedName

data class Shows (
    @SerializedName("results")
    var shows: List<Show> = ArrayList()
)