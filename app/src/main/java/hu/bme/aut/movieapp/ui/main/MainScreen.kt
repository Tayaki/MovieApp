package hu.bme.aut.movieapp.ui.main

import hu.bme.aut.movieapp.db.model.ShowDb

interface MainScreen {
    fun listShows(shows: List<ShowDb>?)
    fun addShow(show: ShowDb)
    fun removeShow(show: ShowDb, position: Int)
    fun showNetworkError(errorMsg: String)
}