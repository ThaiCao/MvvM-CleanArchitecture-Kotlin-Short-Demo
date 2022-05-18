package com.example.mydemo.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mydemo.local.dao.IMovieLocalDao
import com.example.mydemo.local.migration.Migrations
import com.example.mydemo.local.models.movies.MovieLocal
import com.example.mydemo.local.utils.LocalConstants

@Database(
    entities = [
        MovieLocal::class,

    ],
    version = LocalConstants.DB_VERSION,
    exportSchema = false
)
//@TypeConverters(
//    Converters::class)
abstract class AppDatabase  : RoomDatabase(){

    abstract fun movieLocalDao(): IMovieLocalDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        fun buildDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, LocalConstants.DB_NAME)
            .addMigrations(*Migrations.getMigrations())
            .fallbackToDestructiveMigration()
            .build()
    }
}