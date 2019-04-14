package hu.bme.aut.movieapp.interactor.shows.events

import hu.bme.aut.movieapp.model.Show

data class GetShowsEvent (
    var code: Int = 0,
    var shows: List<Show>? = null,
    var throwable: Throwable? = null
)