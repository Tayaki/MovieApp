package hu.bme.aut.movieapp.ui.main

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import github.nisrulz.recyclerviewhelper.RVHItemTouchHelperCallback
import hu.bme.aut.movieapp.R
import hu.bme.aut.movieapp.db.AppDatabase
import hu.bme.aut.movieapp.db.model.ShowDb
import hu.bme.aut.movieapp.injector
import hu.bme.aut.movieapp.model.Show
import hu.bme.aut.movieapp.ui.create.CreateFragment

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), MainScreen {
    @Inject
    lateinit var mainPresenter: MainPresenter
    private var shows: MutableList<ShowDb> = mutableListOf()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        injector.inject(this)

        show_list.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(this, this.shows, mainPresenter)
        show_list.adapter = adapter
        val callback = RVHItemTouchHelperCallback(
            adapter, false, true,
            true
        )
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(show_list)
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.detachScreen()
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.getShows()
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
            R.id.refresh_list -> {
                val dialog = ProgressDialog.show(
                    this, "",
                    "Loading. Please wait...", true
                )
                thread {
                    AppDatabase.getInstance(this@MainActivity).showDao().deleteAll()
                    mainPresenter.getShows()
                }
                Handler().postDelayed({
                    dialog.dismiss()
                }, 5000)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun addShow(show: ShowDb) {
        adapter.add(show)
        Snackbar.make(layout_main, getString(R.string.added), Snackbar.LENGTH_SHORT).show()
    }

    override fun removeShow(show: ShowDb, position: Int) {
        adapter.remove(position)
        Snackbar.make(layout_main, getString(R.string.removed), Snackbar.LENGTH_SHORT).show()
    }

    override fun listShows(shows: List<ShowDb>?) {
        adapter.update(shows!!.toMutableList())
    }

    override fun showNetworkError(errorMsg: String) {
        Snackbar.make(layout_main, errorMsg, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val SHOW_ID = "SHOW_ID"
    }
}
