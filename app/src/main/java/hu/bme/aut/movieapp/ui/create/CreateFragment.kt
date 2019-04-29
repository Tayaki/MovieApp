package hu.bme.aut.movieapp.ui.create

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hu.bme.aut.movieapp.R
import hu.bme.aut.movieapp.db.model.ShowDb
import hu.bme.aut.movieapp.injector
import hu.bme.aut.movieapp.model.Show
import kotlinx.android.synthetic.main.fragment_create.*
import java.util.*
import javax.inject.Inject

class CreateFragment : DialogFragment(), CreateScreen {
    @Inject
    lateinit var createPresenter: CreatePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_save.setOnClickListener {
            val show = ShowDb()
            show.id = (666..999).shuffled().first()
            show.name = create_title.text.toString()
            show.avgRate = create_ratings.text.toString().toDouble()
            var date = Calendar.getInstance()
            date.set(create_first_air.year, create_first_air.month, create_first_air.dayOfMonth)
            show.firstAirDate = Date(date.timeInMillis)
            show.genres = create_genres.text.toString()
            show.seasons = create_seasons.text.toString().toInt()
            show.episodes = create_episodes.text.toString().toInt()
            show.overview = create_plot.text.toString()
            show.status = if (create_ongoing.isChecked)
                getResources().getString(R.string.ongoing) else
                getResources().getString(R.string.completed)
            createPresenter.addShow(show)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        createPresenter.attachScreen(this)
    }

    override fun onDetach() {
        createPresenter.detachScreen()
        super.onDetach()
    }

    override fun closeDialog() {
        dismiss()
    }
}