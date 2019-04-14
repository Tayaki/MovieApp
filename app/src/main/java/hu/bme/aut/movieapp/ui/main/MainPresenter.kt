package hu.bme.aut.movieapp.ui.main

import hu.bme.aut.movieapp.interactor.shows.ShowsInteractor
import hu.bme.aut.movieapp.interactor.shows.events.AddShowEvent
import hu.bme.aut.movieapp.interactor.shows.events.GetShowsEvent
import hu.bme.aut.movieapp.interactor.shows.events.RemoveShowEvent
import hu.bme.aut.movieapp.model.Show
import hu.bme.aut.movieapp.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class MainPresenter @Inject constructor(private val executor: Executor, private val interactor: ShowsInteractor) :
    Presenter<MainScreen>() {

    fun getShows() {
        executor.execute {
            interactor.getShows()
        }
    }

    fun removeShow(show: Show) {
        executor.execute {
            interactor.removeShow(show)
        }
    }

    override fun attachScreen(screen: MainScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetShowsEvent) {
        if (event.throwable != null) {
            showNetworkErrorOnScreen(event.throwable);
        } else {
            if (screen != null) {
                if (event.shows != null) {
                    screen?.listShows(event.shows)
                }

            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: AddShowEvent) {
        if (event.throwable != null) {
            showNetworkErrorOnScreen(event.throwable);
        } else {
            if (screen != null) {
                if (event.show != null) {
                    screen?.addShow(event.show!!)
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: RemoveShowEvent) {
        if (event.throwable != null) {
            showNetworkErrorOnScreen(event.throwable);
        } else {
            if (screen != null) {
                if (event.show != null) {
                    screen?.removeShow(event.show!!)
                }
            }
        }
    }

    fun showNetworkErrorOnScreen(throwable: Throwable?) {
        throwable?.printStackTrace()
        if (screen != null) {
            screen?.showNetworkError(throwable?.message.orEmpty())
        }
    }
}