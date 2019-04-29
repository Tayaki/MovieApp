package hu.bme.aut.movieapp.ui.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import hu.bme.aut.movieapp.R
import hu.bme.aut.movieapp.db.model.ShowDb
import hu.bme.aut.movieapp.injector
import hu.bme.aut.movieapp.ui.main.MainActivity.Companion.SHOW_ID
import kotlinx.android.synthetic.main.activity_details.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), DetailsScreen {
    @Inject
    lateinit var detailsPresenter: DetailsPresenter
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/original"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        injector.inject(this)
    }

    override fun onStart() {
        super.onStart()
        detailsPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        detailsPresenter.detachScreen()
    }

    override fun onResume() {
        super.onResume()
        detailsPresenter.getShow(intent.getIntExtra(SHOW_ID, 100))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showShow(show: ShowDb?) {
        details_title.text = show?.name
        details_ongoing.text = show?.status
        details_first_air.text = SimpleDateFormat("dd. MMM yyyy.", Locale.ENGLISH).format(show?.firstAirDate)
        details_genres.text = show?.genres
        details_avarage_rating.text = show?.avgRate.toString()
        details_seasons.text = show?.seasons.toString()
        details_episodes.text = show?.episodes.toString()
        details_plot.text = show?.overview
        if (show?.url != null && show.url!!.isNotEmpty()) {
            Glide.with(this).load(IMAGE_BASE + show.url).into(details_image)
        } else {
            Glide.with(this).load(R.drawable.no_image).into(details_image)
        }
    }

    override fun showNetworkError(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
    }
}