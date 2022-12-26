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

class AddNewItemFragment : Fragment(R.layout.fragment_item_details) {
    private lateinit var binding: FragmentItemDetailsBinding
    private val viewModel: ItemsViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemDetailsBinding.bind(view)

        binding.btnSave.setOnClickListener {
            val ssid = binding.etSSID.text.toString()
            val password = binding.etPassword.text.toString()
            if (ssid.trim().isNotEmpty()) {
                viewModel.addItem(ssid, password)
                parentFragmentManager.popBackStack()
            }
        }
    }
}