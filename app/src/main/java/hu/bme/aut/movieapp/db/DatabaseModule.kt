package hu.bme.aut.movieapp.db

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideDatabase(): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}