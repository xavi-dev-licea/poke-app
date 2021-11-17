package com.xavidev.pokeapp.data.local.db

import android.content.Context
import androidx.room.Room

class DatabaseManager {

    lateinit var database: AppDatabase

    companion object {
        val instance = DatabaseManager()
    }

    fun initDatabase(context: Context) {
        createDatabase(context)
    }

    private fun createDatabase(context: Context) {
        database = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}