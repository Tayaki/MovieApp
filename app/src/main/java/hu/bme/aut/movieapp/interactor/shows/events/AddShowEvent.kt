package hu.bme.aut.movieapp.interactor.shows.events

import hu.bme.aut.movieapp.model.Show

data class AddShowEvent (
    var code: Int = 0,
    var show: Show? = null,
    var throwable: Throwable? = null
)