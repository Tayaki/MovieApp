package hu.bme.aut.movieapp

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.aut.movieapp.interactor.shows.ShowsInteractor
import hu.bme.aut.movieapp.ui.create.CreatePresenter
import hu.bme.aut.movieapp.ui.details.DetailsPresenter
import hu.bme.aut.movieapp.ui.main.MainPresenter
import hu.bme.aut.movieapp.utils.UiExecutor
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class TestModule(private val context: Context) {

    @Provides
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideMainPresenter(executor: Executor, interactor: ShowsInteractor) = MainPresenter(executor, interactor)

    @Provides
    @Singleton
    fun provideDetailsPresenter(executor: Executor, interactor: ShowsInteractor) =
        DetailsPresenter(executor, interactor)

    @Provides
    @Singleton
    fun provideCreatePresenter(executor: Executor, tvShowsInteractor: ShowsInteractor) =
        CreatePresenter(executor, tvShowsInteractor)

    @Provides
    @Singleton
    fun provideNetworkExecutor(): Executor = UiExecutor()
}