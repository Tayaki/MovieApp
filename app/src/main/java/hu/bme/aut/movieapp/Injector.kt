package hu.bme.aut.movieapp

import android.app.Activity
import androidx.fragment.app.Fragment

val Activity.injector: MovieAppApplicationComponent
    get() {
        return (this.applicationContext as MovieAppApplication).injector
    }

val Fragment.injector: MovieAppApplicationComponent
    get() {
        return (this.context!!.applicationContext as MovieAppApplication).injector
    }