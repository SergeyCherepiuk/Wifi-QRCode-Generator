package com.example.wifiqrcodes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wifiqrcodes.database.Item
import com.example.wifiqrcodes.utils.QRCodeGenerator
import com.example.wifiqrcodes.R
import com.example.wifiqrcodes.interfaces.ViewPagerAdapterCallback

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
        val btnSend: ImageButton = itemView.findViewById(R.id.btnSend)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.viewpager_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.ivQRCode.setImageBitmap(QRCodeGenerator.generateQRCode(currentItem.getQRCode()))
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
        holder.btnSend.setOnClickListener {
            // TODO: Send implicit intent (with Uri of QR code bitmap and "image/*" MIME type)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}