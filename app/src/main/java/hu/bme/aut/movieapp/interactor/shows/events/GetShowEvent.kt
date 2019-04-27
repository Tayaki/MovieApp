package hu.bme.aut.movieapp.interactor.shows.events

import hu.bme.aut.movieapp.db.model.ShowDb

data class GetShowEvent (
    var code: Int = 0,
    var show: ShowDb? = null,
    var throwable: Throwable? = null
)