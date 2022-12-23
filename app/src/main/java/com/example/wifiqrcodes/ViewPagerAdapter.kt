package com.example.wifiqrcodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(
    private val itemList: MutableList<Item>,
    private val editCallback: ViewPagerAdapterCallback,
    private val deleteCallback: ViewPagerAdapterCallback,
) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivQRCode: ImageView = itemView.findViewById(R.id.ivQRCode)
        val tvSSID: TextView = itemView.findViewById(R.id.tvSSID)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
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
        holder.btnEdit.setOnClickListener {
            editCallback.onClick(currentItem)
        }
        holder.btnDelete.setOnClickListener {
            deleteCallback.onClick(currentItem)
            itemList.remove(currentItem)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemList.size)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}