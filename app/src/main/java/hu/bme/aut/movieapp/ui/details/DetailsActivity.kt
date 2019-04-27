package hu.bme.aut.movieapp.ui.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hu.bme.aut.movieapp.R
import hu.bme.aut.movieapp.db.model.ShowDb
import hu.bme.aut.movieapp.injector
import hu.bme.aut.movieapp.ui.main.MainActivity.Companion.SHOW_ID
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), DetailsScreen {
    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        injector.inject(this)

        detailsPresenter.getShow(intent.getIntExtra(SHOW_ID, 100))
    }

    override fun onStart() {
        super.onStart()
        detailsPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        detailsPresenter.detachScreen()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showShow(show: ShowDb?) {
        details_content.text = show.toString()
    }

    override fun showNetworkError(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
    }
}