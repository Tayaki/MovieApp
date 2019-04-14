package hu.bme.aut.movieapp

import android.app.Application
import hu.bme.aut.movieapp.ui.UIModule

class MovieAppApplication : Application() {
    lateinit var injector: MovieAppApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerMovieAppApplicationComponent.builder().uIModule(UIModule(this)).build()
    }
}