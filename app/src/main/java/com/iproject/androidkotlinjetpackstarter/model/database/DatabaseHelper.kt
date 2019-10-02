package com.iproject.androidkotlinjetpackstarter.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GithubUser::class], version = 1)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun githubUserDao(): GithubUserDao

    companion object {
        @Volatile private var instance: DatabaseHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DatabaseHelper::class.java,
            "github_users"
        ).build()
    }
}