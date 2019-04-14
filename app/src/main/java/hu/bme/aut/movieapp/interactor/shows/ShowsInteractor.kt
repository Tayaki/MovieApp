package hu.bme.aut.movieapp.interactor.shows

import android.util.Log
import hu.bme.aut.movieapp.interactor.shows.events.AddShowEvent
import hu.bme.aut.movieapp.interactor.shows.events.GetShowEvent
import hu.bme.aut.movieapp.interactor.shows.events.GetShowsEvent
import hu.bme.aut.movieapp.interactor.shows.events.RemoveShowEvent
import hu.bme.aut.movieapp.model.Show
import hu.bme.aut.movieapp.network.NetworkConfig
import hu.bme.aut.movieapp.network.ShowsApi
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class ShowsInteractor @Inject constructor(private var showsApi: ShowsApi) {
    fun getShows() {
        val event = GetShowsEvent()

        try {
            val showsQueryCall = showsApi.getShows(NetworkConfig.API_KEY, "en-US", 1)

            val response = showsQueryCall.execute()
            Log.d("Reponse", response.body().toString())
            if (response.code() != 200) {
                throw Exception("Result code is not 200")
            }
            event.code = response.code()
            event.shows = response.body()?.shows
            EventBus.getDefault().post(event)
        } catch (exception: Exception) {
            event.throwable = exception
            EventBus.getDefault().post(event)
        }
    }

    fun getShow(id: Int) {
        val event = GetShowEvent()

        try {
            val showsQueryCall = showsApi.getShowDetails(id, NetworkConfig.API_KEY, "en-US")

            val response = showsQueryCall.execute()
            Log.d("Reponse", response.body().toString())
            if (response.code() != 200) {
                throw Exception("Result code is not 200")
            }
            event.code = response.code()
            event.show = response.body()
            EventBus.getDefault().post(event)
        } catch (exception: Exception) {
            event.throwable = exception
            EventBus.getDefault().post(event)
        }
    }

    fun addShow(show: Show) {
        val event = AddShowEvent(show = show)

        EventBus.getDefault().post(event)
    }

    fun removeShow(show: Show) {
        val event = RemoveShowEvent(show = show)

        EventBus.getDefault().post(event)
    }
}