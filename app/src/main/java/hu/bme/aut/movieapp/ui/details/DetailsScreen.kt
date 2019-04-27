package hu.bme.aut.movieapp.ui.details

import hu.bme.aut.movieapp.db.model.ShowDb

interface DetailsScreen {
    fun showShow(show: ShowDb?)
    fun showNetworkError(errorMsg: String)
}