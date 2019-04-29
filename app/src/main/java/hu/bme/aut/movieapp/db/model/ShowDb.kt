package hu.bme.aut.movieapp.db.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "show")
data class ShowDb(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "firstAirDate")
    var firstAirDate: Date? = null,
    @ColumnInfo(name = "url")
    var url: String? = null,
    @ColumnInfo(name = "avgRate")
    var avgRate: Double? = null,
    @ColumnInfo(name = "seasons")
    var seasons: Int? = null,
    @ColumnInfo(name = "episodes")
    var episodes : Int? = null,
    @ColumnInfo(name = "overview")
    var overview: String? = null,
    @ColumnInfo(name = "genres")
    var genres: String? = null,
    @ColumnInfo(name = "status")
    var status: String? = null
)