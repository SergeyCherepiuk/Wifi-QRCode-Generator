package com.example.wifiqrcodes

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import java.io.*

@RequiresApi(Build.VERSION_CODES.R)
class ItemsViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "ItemsViewModel"
    }

    private var itemsList: ArrayList<Item> = ArrayList()
    private val context = getApplication<Application>().applicationContext
    private val file = File(context.filesDir, "wifi_data.txt")

    init {
        if (file.exists() && file.isFile) {
            BufferedReader(FileReader(file)).use { br ->
                br.lines().forEach { qr ->
                    val ssid = qr.substring(qr.indexOf("S:") + 2, qr.indexOf("P:") - 1)
                    itemsList.add(Item(qr, ssid))
                }
            }
        } else {
            file.createNewFile()
        }
    }

    fun addItem(ssid: String, password: String) {
        val item = Item("WIFI:T:WPA;S:${ssid};P:${password};H:;;", ssid)
        itemsList.add(item)
        val fos = FileOutputStream(file, true)
        fos.write(item.qr.plus("\n").toByteArray())
        fos.close()
    }

    fun getItems(): ArrayList<Item> {
        Log.d(TAG, "getItems: $itemsList")
        return itemsList
    }
}