package com.example.wifiqrcodes.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.*
import com.example.wifiqrcodes.*
import com.example.wifiqrcodes.adapters.ViewPagerAdapter
import com.example.wifiqrcodes.viewmodels.ItemsViewModel
import com.example.wifiqrcodes.databinding.FragmentItemListBinding
import com.google.android.material.tabs.TabLayoutMediator

class ItemListFragment : Fragment(R.layout.fragment_item_list) {
    private lateinit var binding: FragmentItemListBinding
    private val viewModel: ItemsViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemListBinding.bind(view)

        binding.viewPager.adapter = ViewPagerAdapter(
            fragment = this,
            itemList = viewModel.getAllItems().toMutableList(),
        )

        viewModel.adapter = binding.viewPager.adapter as ViewPagerAdapter

        // TODO: Set different icon to last page (add new item page)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->

        }.attach()
    }
}