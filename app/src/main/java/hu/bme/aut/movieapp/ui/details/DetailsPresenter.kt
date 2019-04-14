package hu.bme.aut.movieapp.ui.details

import hu.bme.aut.movieapp.interactor.shows.ShowsInteractor
import hu.bme.aut.movieapp.interactor.shows.events.GetShowEvent
import hu.bme.aut.movieapp.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class DetailsPresenter @Inject constructor(private val executor: Executor, private val interactor: ShowsInteractor) :
    Presenter<DetailsScreen>() {

    fun getShow(id: Int) {
        executor.execute {
            interactor.getShow(id)
        }
    }

    override fun attachScreen(screen: DetailsScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetShowEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showNetworkError(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.show != null) {
                    screen?.showShow(event.show)
                }
            }
        }
    }
}