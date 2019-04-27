package hu.bme.aut.movieapp.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import github.nisrulz.recyclerviewhelper.RVHAdapter
import github.nisrulz.recyclerviewhelper.RVHViewHolder
import hu.bme.aut.movieapp.R
import hu.bme.aut.movieapp.db.model.ShowDb
import hu.bme.aut.movieapp.model.Show
import hu.bme.aut.movieapp.ui.details.DetailsActivity
import hu.bme.aut.movieapp.ui.main.MainActivity.Companion.SHOW_ID
import kotlinx.android.synthetic.main.item.view.*
import java.util.*

class MainAdapter constructor(
    private val context: Context,
    private var shows: MutableList<ShowDb>,
    private val presenter: MainPresenter
) : RecyclerView.Adapter<MainAdapter.ViewHolder>(), RVHAdapter {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val show = shows[position]

        holder.name.text = show.name
        holder.score.text = show.avgRate.toString()
        holder.name.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(SHOW_ID, show.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = shows.size

    override fun onItemDismiss(position: Int, direction: Int) {
        presenter.removeShow(shows[position], position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        swap(fromPosition, toPosition)
        return false
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), RVHViewHolder {
        var name = view.show_name!!
        var score = view.score!!

        override fun onItemSelected(actionstate: Int) {
        }

        override fun onItemClear() {
        }
    }

    private fun swap(firstPosition: Int, secondPosition: Int) {
        Collections.swap(shows, firstPosition, secondPosition)
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        shows.remove(shows[position])
        notifyItemRemoved(position)
    }

    fun update(shows: MutableList<ShowDb>) {
        this.shows = shows
        notifyDataSetChanged()
    }

    fun add(show: ShowDb) {
        this.shows.add(show)
        notifyItemInserted(shows.size - 1)
    }
}