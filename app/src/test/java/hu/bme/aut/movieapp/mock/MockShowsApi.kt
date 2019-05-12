package hu.bme.aut.movieapp.mock

import hu.bme.aut.movieapp.model.Genre
import hu.bme.aut.movieapp.model.Show
import hu.bme.aut.movieapp.model.Shows
import hu.bme.aut.movieapp.network.ShowsApi
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

class MockShowsApi : ShowsApi {
    private val show = Show(
        666, "Lucifer", Date(2016, 4, 25), "-", 8.2,  4, 67,
        "Best show ever!", listOf(Genre("Crime"), Genre("Drama"), Genre("Fantasy")), "Ended"
    )

    override fun getShows(apiKey: String, language: String, page: Int): Call<Shows> {
        val call = object : Call<Shows> {
            val shows = listOf(show, show, show)

            @Throws(IOException::class)
            override fun execute(): Response<Shows> {
                return Response.success(Shows(shows))
            }

            override fun enqueue(callback: Callback<Shows>) {

            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {

            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun clone(): Call<Shows> {
                return this
            }

            override fun request(): Request? {
                return null
            }
        }

        return call
    }

    override fun getShowDetails(id: Int, apiKey: String, language: String): Call<Show> {
        val call = object : Call<Show> {
            @Throws(IOException::class)
            override fun execute(): Response<Show> {
                return Response.success(show)
            }

            override fun enqueue(callback: Callback<Show>) {

            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {

            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun clone(): Call<Show> {
                return this
            }

            override fun request(): Request? {
                return null
            }
        }

        return call
    }
}