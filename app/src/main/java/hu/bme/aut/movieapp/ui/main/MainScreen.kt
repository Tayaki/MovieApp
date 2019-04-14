package hu.bme.aut.movieapp.ui.main

import hu.bme.aut.movieapp.model.Show

interface MainScreen {
    fun listShows(shows: List<Show>?)
    fun addShow(show: Show)
    fun removeShow(show: Show)
    fun showNetworkError(errorMsg: String)
}