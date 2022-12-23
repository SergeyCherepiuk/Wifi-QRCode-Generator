package com.example.wifiqrcodes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {
    @Query("SELECT * FROM item_table")
    suspend fun getAllItems(): List<Item>

    @Query("SELECT * FROM item_table WHERE ssid LIKE :ssid")
    suspend fun findItemBySSID(ssid: String): List<Item>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)
}