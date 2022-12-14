package com.example.wifiqrcodes.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.wifiqrcodes.viewmodels.ItemsViewModel
import com.example.wifiqrcodes.R
import com.example.wifiqrcodes.database.Item
import com.example.wifiqrcodes.databinding.FragmentItemDetailsBinding

class EditItemDetailsFragment(private val item: Item) : Fragment(R.layout.fragment_item_details) {
    private lateinit var binding: FragmentItemDetailsBinding
    private val viewModel: ItemsViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemDetailsBinding.bind(view)

        binding.etSSID.setText(item.ssid)
        binding.etPassword.setText(item.password)

        binding.btnSave.setOnClickListener {
            item.ssid = binding.etSSID.text.toString()
            item.password = binding.etPassword.text.toString()
            viewModel.update(item)
            parentFragmentManager.popBackStack()
        }

        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}