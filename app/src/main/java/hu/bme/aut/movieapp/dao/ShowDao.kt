package hu.bme.aut.movieapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import hu.bme.aut.movieapp.db.model.ShowDb

@Dao
public interface ShowDao {
    @Query("SELECT * from show")
    fun getAllShow(): List<ShowDb>

    @Query("SELECT * from show where id = :id")
    fun getShowById(id: Int): ShowDb

    @Insert
    fun insert(showDb: ShowDb)

    @Delete
    fun delete(showDb: ShowDb)

    @Query("DELETE FROM show WHERE id = :id")
    fun deleteById(id: Int)

    @Query("DELETE FROM show")
    fun deleteAll()
}