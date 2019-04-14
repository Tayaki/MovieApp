package hu.bme.aut.movieapp.ui.main

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import github.nisrulz.recyclerviewhelper.RVHItemTouchHelperCallback
import hu.bme.aut.movieapp.R
import hu.bme.aut.movieapp.injector
import hu.bme.aut.movieapp.model.Show
import hu.bme.aut.movieapp.ui.create.CreateFragment

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainScreen {
    @Inject
    lateinit var mainPresenter: MainPresenter
    private lateinit var shows: MutableList<Show>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        injector.inject(this)

        mainPresenter.getShows()
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.detachScreen()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.open_create -> {
                CreateFragment().show(supportFragmentManager, "CREATE")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun addShow(show: Show) {
        shows.add(show)
        show_list.adapter?.notifyDataSetChanged()
        Snackbar.make(layout_main, getString(R.string.added), Snackbar.LENGTH_SHORT).show()
    }

    override fun removeShow(show: Show) {
        val position = shows.indexOf(show)
        shows.remove(show)
        show_list.adapter?.notifyItemRemoved(position)
        Snackbar.make(layout_main, getString(R.string.removed), Snackbar.LENGTH_SHORT).show()
    }

    override fun listShows(shows: List<Show>?) {
        this.shows = shows?.toMutableList()!!
        show_list.layoutManager = LinearLayoutManager(this)
        val adapter = MainAdapter(this, this.shows, mainPresenter)
        show_list.adapter = adapter

        val callback = RVHItemTouchHelperCallback(
            adapter, false, true,
            true
        )
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(show_list)
    }

    override fun showNetworkError(errorMsg: String) {
        Snackbar.make(layout_main, errorMsg, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val SHOW_ID = "SHOW_ID"
    }
}
