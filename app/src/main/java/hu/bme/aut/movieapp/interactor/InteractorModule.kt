package hu.bme.aut.movieapp.interactor

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.aut.movieapp.interactor.shows.ShowsInteractor
import hu.bme.aut.movieapp.network.ShowsApi
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideShowsInteractor(showsApi: ShowsApi, context: Context) = ShowsInteractor(showsApi, context)
}