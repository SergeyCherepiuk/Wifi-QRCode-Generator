package com.example.wifiqrcodes

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.wifiqrcodes.databinding.FragmentAddNewItemBinding

class AddNewItemFragment : Fragment(R.layout.fragment_add_new_item) {
    private lateinit var binding: FragmentAddNewItemBinding
    private val viewModel: ItemsViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddNewItemBinding.bind(view)

        binding.btnSave.setOnClickListener {
            val ssid = binding.etSSID.text.toString()
            val password = binding.etPassword.text.toString()
            if (ssid.isNotEmpty()) {
                viewModel.addItem(ssid, password)
            }
            parentFragmentManager.popBackStack()
        }
    }
}