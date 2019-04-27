package hu.bme.aut.movieapp.interactor.shows

import android.content.Context
import android.util.Log
import hu.bme.aut.movieapp.db.AppDatabase
import hu.bme.aut.movieapp.db.model.ShowDb
import hu.bme.aut.movieapp.interactor.shows.events.AddShowEvent
import hu.bme.aut.movieapp.interactor.shows.events.GetShowEvent
import hu.bme.aut.movieapp.interactor.shows.events.GetShowsEvent
import hu.bme.aut.movieapp.interactor.shows.events.RemoveShowEvent
import hu.bme.aut.movieapp.model.Show
import hu.bme.aut.movieapp.network.NetworkConfig
import hu.bme.aut.movieapp.network.ShowsApi
import org.greenrobot.eventbus.EventBus
import org.modelmapper.ModelMapper
import javax.inject.Inject

class ShowsInteractor @Inject constructor(private var showsApi: ShowsApi, private var context: Context) {
    fun getShows() {
        val event = GetShowsEvent()

        try {
            val shows = AppDatabase.getInstance(context).showDao().getAllShow().toMutableList()
            var showDbList = mutableListOf<ShowDb>()

            if (shows.isEmpty()) {
                val showsQueryCall = showsApi.getShows(NetworkConfig.API_KEY, "en-US", 1)
                val response = showsQueryCall.execute()
                Log.d("Reponse", response.body().toString())
                if (response.code() != 200) {
                    throw Exception("Result code is not 200")
                }

                response.body()?.shows?.forEach {
                    val show = getShowDetails(it.id!!)
                    showDbList.add(saveShow(show))
                }
            } else {
                showDbList = shows
            }

            event.code = 200
            event.shows = showDbList
            EventBus.getDefault().post(event)
        } catch (exception: Exception) {
            event.throwable = exception
            EventBus.getDefault().post(event)
        }
    }

    fun getShow(id: Int) {
        val event = GetShowEvent()

        try {
            val show = AppDatabase.getInstance(context).showDao().getShowById(id)

            event.code = 200
            event.show = show
            EventBus.getDefault().post(event)
        } catch (exception: Exception) {
            event.throwable = exception
            EventBus.getDefault().post(event)
        }
    }

    fun addShow(show: ShowDb) {
        AppDatabase.getInstance(context).showDao().insert(show)
        val event = AddShowEvent(show = show)
        EventBus.getDefault().post(event)
    }

    fun removeShow(show: ShowDb, position: Int) {
        AppDatabase.getInstance(context).showDao().deleteById(show.id!!)
        val event = RemoveShowEvent(show = show, position = position)
        EventBus.getDefault().post(event)
    }

    private fun mapShowTpDb(show: Show) = ModelMapper().map(show, ShowDb::class.java)

    private fun getShowDetails(id: Int): Show {
        val showsCall = showsApi.getShowDetails(id, NetworkConfig.API_KEY, "en-US")

        val response = showsCall.execute()
        Log.d("Reponse", response.body().toString())
        if (response.code() != 200) {
            throw Exception("Result code is not 200")
        }
        return response.body()!!
    }

    private fun saveShow(show: Show): ShowDb {
        val showDb = mapShowTpDb(show)
        AppDatabase.getInstance(context).showDao().insert(showDb)
        return showDb
    }
}