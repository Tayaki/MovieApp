package hu.bme.aut.movieapp.ui.create

import hu.bme.aut.movieapp.interactor.shows.ShowsInteractor
import hu.bme.aut.movieapp.interactor.shows.events.AddShowEvent
import hu.bme.aut.movieapp.model.Show
import hu.bme.aut.movieapp.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class CreatePresenter @Inject constructor(
    private val executor: Executor,
    private val interactor: ShowsInteractor
) : Presenter<CreateScreen>() {

    fun addShow(show: Show) {
        executor.execute {
            interactor.addShow(show)
        }
    }

    override fun attachScreen(screen: CreateScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: AddShowEvent) {
        if (screen != null) {
            if (event.show != null) {
                screen?.closeDialog()
            }
        }
    }
}