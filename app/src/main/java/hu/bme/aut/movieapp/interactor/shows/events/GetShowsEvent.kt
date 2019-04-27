package hu.bme.aut.movieapp.interactor.shows.events

import hu.bme.aut.movieapp.db.model.ShowDb

data class GetShowsEvent (
    var code: Int = 0,
    var shows: List<ShowDb>? = null,
    var throwable: Throwable? = null
)