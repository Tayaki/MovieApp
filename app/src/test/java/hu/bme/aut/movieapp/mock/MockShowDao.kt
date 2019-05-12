package hu.bme.aut.movieapp.mock

import android.util.Log
import androidx.room.Dao
import hu.bme.aut.movieapp.dao.ShowDao
import hu.bme.aut.movieapp.db.model.ShowDb
import java.util.*

@Dao
abstract class MockShowDao : ShowDao {
    override fun getAllShow(): List<ShowDb> {
        val show = ShowDb(
            666, "Lucifer", Date(2016, 4, 25), "-", 8.2,  4, 67,
            "Best show ever!", "Crime, Drama, Fantasy", "Ongoing"
        )
        return listOf(show, show, show)
    }

    override fun getShowById(id: Int): ShowDb {
        return ShowDb(
            666, "Lucifer", Date(2016, 4, 25), "-", 8.2,  4, 67,
            "Best show ever!", "Crime, Drama, Fantasy", "Ongoing"
        )
    }

    override fun insert(showDb: ShowDb) {
        Log.d("Database", "Record inserted.")
    }

    override fun delete(showDb: ShowDb) {
        Log.d("Database", "Record deleted.")

    }

    override fun deleteById(id: Int) {
        Log.d("Database", "Record deleted by id.")
    }

    override fun deleteAll() {
        Log.d("Database", "Records deleted.")
    }
}