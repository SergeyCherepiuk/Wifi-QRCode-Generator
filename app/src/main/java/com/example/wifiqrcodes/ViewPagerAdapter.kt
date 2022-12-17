package com.example.wifiqrcodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivQRCode: ImageView = itemView.findViewById(R.id.ivQRCode)
        val tvSSID: TextView = itemView.findViewById(R.id.tvSSID)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapter.ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.viewpager_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.ivQRCode.setImageBitmap(QRCode().generateQRCode(currentItem.getQRCode()))
        holder.tvSSID.text = currentItem.ssid
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}