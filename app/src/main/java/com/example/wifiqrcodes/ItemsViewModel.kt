package com.example.wifiqrcodes

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import kotlinx.coroutines.*

@RequiresApi(Build.VERSION_CODES.R)
class ItemsViewModel(application: Application) : AndroidViewModel(application){
    private val appDatabase = Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java,
        "app_database"
    ).build()

    @OptIn(DelicateCoroutinesApi::class)
    fun addItem(ssid: String, password: String) {
        GlobalScope.launch(Dispatchers.IO) {
            appDatabase.itemDao().insert(Item(null, ssid, password))
        }
    }

    fun getAllItems(): List<Item> {
        return runBlocking {
            appDatabase.itemDao().getAllItems()
        }
    }
}