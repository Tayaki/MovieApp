package hu.bme.aut.movieapp.ui.details

import hu.bme.aut.movieapp.model.Show

interface DetailsScreen {
    fun showShow(show: Show?)
    fun showNetworkError(errorMsg: String)
}