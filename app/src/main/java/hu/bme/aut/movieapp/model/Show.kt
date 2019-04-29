package hu.bme.aut.movieapp.model

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class Show (
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("first_air_date")
    var firstAirDate: Date? = null,
    @SerializedName("backdrop_path")
    var url: String? = null,
    @SerializedName("vote_average")
    var avgRate: Double? = null,
    @SerializedName("number_of_seasons")
    var seasons: Int? = null,
    @SerializedName("number_of_episodes")
    var episodes : Int? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("genres")
    var genres: List<Genre> = ArrayList(),
    @SerializedName("status")
    var status: String? = null
)