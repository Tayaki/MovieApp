package hu.bme.aut.movieapp.network

import hu.bme.aut.movieapp.model.Show
import hu.bme.aut.movieapp.model.Shows
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowsApi {
    @GET("tv/popular")
    fun getShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<Shows>

    @GET("tv/{id}")
    fun getShowDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Show>
}