package hu.bme.aut.movieapp.mock

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.TypeConverters
import hu.bme.aut.movieapp.db.AppDatabase
import hu.bme.aut.movieapp.db.DateConverter
import hu.bme.aut.movieapp.db.model.ShowDb

@Database(entities = [(ShowDb::class)], version = 5, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MockAppDatabase : AppDatabase() {
    abstract override fun showDao(): MockShowDao

    companion object {
        private var sInstance: MockAppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MockAppDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .inMemoryDatabaseBuilder(context.applicationContext, MockAppDatabase::class.java)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return sInstance!!
        }

    }
}