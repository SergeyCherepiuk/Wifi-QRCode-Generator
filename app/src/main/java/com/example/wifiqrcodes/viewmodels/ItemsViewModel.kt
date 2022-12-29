package com.example.wifiqrcodes.viewmodels

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.example.wifiqrcodes.adapters.ViewPagerAdapter
import com.example.wifiqrcodes.database.AppDatabase
import com.example.wifiqrcodes.database.Item
import kotlinx.coroutines.*

@RequiresApi(Build.VERSION_CODES.R)
class ItemsViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var adapter: ViewPagerAdapter
    lateinit var itemList: MutableList<Item>
    private val appDatabase = Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java,
        "app_database"
    ).build()

    @OptIn(DelicateCoroutinesApi::class)
    fun addItem(item: Item) {
        GlobalScope.launch(Dispatchers.IO) {
            appDatabase.itemDao().insert(item)
        }
    }

    fun getAllItems(): List<Item> {
        return runBlocking {
            itemList = appDatabase.itemDao().getAllItems().toMutableList()
            itemList
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun update(item: Item) {
        GlobalScope.launch {
            appDatabase.itemDao().update(item)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun delete(item: Item) {
        GlobalScope.launch {
            appDatabase.itemDao().delete(item)
        }
        itemList.remove(item)
    }
}