package com.example.wifiqrcodes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "ssid") var ssid: String,
    @ColumnInfo(name = "password") var password: String,
) {
    fun getQRCode(): String {
        return "WIFI:T:WPA;S:${ssid};P:${password};H:;;"
    }
}