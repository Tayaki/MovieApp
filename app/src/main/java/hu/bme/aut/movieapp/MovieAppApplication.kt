package hu.bme.aut.movieapp

import android.app.Application
import hu.bme.aut.movieapp.db.DatabaseModule
import hu.bme.aut.movieapp.ui.UIModule

class MovieAppApplication : Application() {
    lateinit var injector: MovieAppApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerMovieAppApplicationComponent.builder().databaseModule(DatabaseModule(this)).uIModule(UIModule(this)).build()
    }
}