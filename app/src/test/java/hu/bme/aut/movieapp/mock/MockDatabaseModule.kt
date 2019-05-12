package hu.bme.aut.movieapp.mock

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.aut.movieapp.db.AppDatabase
import javax.inject.Singleton

@Module
class MockDatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideDatabase(): AppDatabase {
        return MockAppDatabase.getInstance(context)
    }
}