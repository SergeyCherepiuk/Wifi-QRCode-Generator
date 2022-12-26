package com.example.wifiqrcodes.interfaces

import com.example.wifiqrcodes.database.Item

interface ViewPagerAdapterCallback {
    fun onClick(item: Item)
}