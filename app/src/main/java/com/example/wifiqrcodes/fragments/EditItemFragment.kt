package com.example.wifiqrcodes.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.wifiqrcodes.database.ItemsViewModel
import com.example.wifiqrcodes.R
import com.example.wifiqrcodes.databinding.FragmentItemDetailsBinding

class EditItemFragment : Fragment(R.layout.fragment_item_details) {
    private lateinit var binding: FragmentItemDetailsBinding
    private val viewModel: ItemsViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemDetailsBinding.bind(view)

        binding.etSSID.setText(viewModel.currentItem.ssid)
        binding.etPassword.setText(viewModel.currentItem.password)

        binding.btnSave.setOnClickListener {
            viewModel.currentItem.ssid = binding.etSSID.text.toString()
            viewModel.currentItem.password = binding.etPassword.text.toString()
            viewModel.update(viewModel.currentItem)
            parentFragmentManager.popBackStack()
        }
    }
}