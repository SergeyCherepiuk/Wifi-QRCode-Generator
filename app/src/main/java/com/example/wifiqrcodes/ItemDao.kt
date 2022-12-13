package com.example.wifiqrcodes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {
    @Query("SELECT * FROM item_table")
    fun getAllItems(): List<Item>

    @Query("SELECT * FROM item_table WHERE ssid LIKE :ssid")
    fun findItemBySSID(ssid: String): List<Item>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: Item)

    @Delete
    fun delete(item: Item)
}