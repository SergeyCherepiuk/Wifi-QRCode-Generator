package com.example.wifiqrcodes.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.wifiqrcodes.R
import com.example.wifiqrcodes.databinding.FragmentAddItemBinding

class AddItemFragment : Fragment(R.layout.fragment_add_item) {
    companion object {
        private const val ADD_NEW_ITEM_TAG = "com.example.wifiqrcodes.ADD_NEW_ITEM"
    }

    private lateinit var binding: FragmentAddItemBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddItemBinding.bind(view)

        binding.btnAddItem.setOnClickListener {
            parentFragment?.parentFragmentManager?.commit {
                setReorderingAllowed(true)
                replace<AddItemDetailsFragment>(R.id.fragmentContainerView)
                addToBackStack(ADD_NEW_ITEM_TAG)
            }
        }
    }
}