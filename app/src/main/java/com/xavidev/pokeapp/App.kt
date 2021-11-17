package com.xavidev.pokeapp

import android.app.Application
import com.xavidev.pokeapp.data.local.db.DatabaseManager

class App : Application() {

    override fun onCreate() {
        DatabaseManager.instance.initDatabase(applicationContext)
        super.onCreate()
    }
}