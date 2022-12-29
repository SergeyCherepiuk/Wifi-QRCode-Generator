package com.example.wifiqrcodes.fragments

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.example.wifiqrcodes.R
import com.example.wifiqrcodes.database.Item
import com.example.wifiqrcodes.databinding.ViewpagerItemBinding
import com.example.wifiqrcodes.utils.QRCodeGenerator
import com.example.wifiqrcodes.viewmodels.ItemsViewModel

class ItemFragment(private val item: Item): Fragment(R.layout.viewpager_item) {
    companion object {
        private const val EDIT_ITEM_TAG = "com.example.wifiqrcodes.EDIT_ITEM"
    }

    private lateinit var binding: ViewpagerItemBinding
    private val viewModel: ItemsViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ViewpagerItemBinding.bind(view)

        binding.ivQRCode.setImageBitmap(QRCodeGenerator.generateQRCode(item.getQRCode()))
        binding.tvSSID.text = item.ssid

        binding.btnEditItem.setOnClickListener {
            parentFragment?.parentFragmentManager?.commit {
                setReorderingAllowed(true)
                replace(R.id.fragmentContainerView, EditItemDetailsFragment(item))
                addToBackStack(EDIT_ITEM_TAG)
            }
        }
        binding.btnDeleteItem.setOnClickListener {
            viewModel.delete(viewModel.adapter.removeItem(viewModel.itemList.indexOf(item)))
        }
        binding.btnSendItem.setOnClickListener {
            // TODO: Send implicit intent (with Uri of QR code bitmap and "image/*" MIME type)
            val bitmap: Bitmap = QRCodeGenerator.generateQRCode(item.getQRCode())
        }
    }
}