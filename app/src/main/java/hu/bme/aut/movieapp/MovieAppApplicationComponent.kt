package hu.bme.aut.movieapp

import dagger.Component
import hu.bme.aut.movieapp.interactor.InteractorModule
import hu.bme.aut.movieapp.network.NetworkModule
import hu.bme.aut.movieapp.ui.UIModule
import hu.bme.aut.movieapp.ui.create.CreateFragment
import hu.bme.aut.movieapp.ui.details.DetailsActivity
import hu.bme.aut.movieapp.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, NetworkModule::class, InteractorModule::class])
interface MovieAppApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(detailsActivity: DetailsActivity)
    fun inject(createFragment: CreateFragment)
}