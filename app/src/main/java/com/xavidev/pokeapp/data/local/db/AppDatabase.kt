package com.xavidev.pokeapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "poke_database"
const val EXPORT_SCHEMA = false

@Database(
    entities = [],
    version = DATABASE_VERSION,
    exportSchema = EXPORT_SCHEMA
)
abstract class AppDatabase:RoomDatabase() {

}