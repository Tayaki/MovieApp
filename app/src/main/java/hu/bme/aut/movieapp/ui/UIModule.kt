package hu.bme.aut.movieapp.ui

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.aut.movieapp.interactor.shows.ShowsInteractor
import hu.bme.aut.movieapp.ui.create.CreatePresenter
import hu.bme.aut.movieapp.ui.details.DetailsPresenter
import hu.bme.aut.movieapp.ui.main.MainPresenter
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun mainPresenter(executor: Executor, showsInteractor: ShowsInteractor) =
        MainPresenter(executor, showsInteractor)

    @Provides
    @Singleton
    fun detailsPresenter(executor: Executor, showsInteractor: ShowsInteractor) =
        DetailsPresenter(executor, showsInteractor)


    @Provides
    @Singleton
    fun createPresenter(executor: Executor, showsInteractor: ShowsInteractor) =
        CreatePresenter(executor, showsInteractor)

    @Provides
    @Singleton
    fun networkExecutor(): Executor = Executors.newFixedThreadPool(1)
}