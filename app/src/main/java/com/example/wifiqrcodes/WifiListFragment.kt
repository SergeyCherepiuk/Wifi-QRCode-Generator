package com.example.wifiqrcodes

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.wifiqrcodes.databinding.FragmentWifiListBinding
import androidx.fragment.app.*
import com.google.android.material.tabs.TabLayoutMediator

class WifiListFragment : Fragment(R.layout.fragment_wifi_list) {
    companion object {
        private const val ADD_NEW_ITEM_TAG = "com.example.wifiqrcodes.ADD_NEW_ITEM"
    }

    private lateinit var binding: FragmentWifiListBinding
    private val viewModel: ItemsViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWifiListBinding.bind(view)

        binding.fabAdd.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<AddNewItemFragment>(R.id.fragmentContainerView)
                addToBackStack(ADD_NEW_ITEM_TAG)
            }
        }

        binding.viewPager.adapter = ViewPagerAdapter(viewModel.getAllItems())
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            //Some implementation
        }.attach()
    }

    // TODO: Create fragment to observe details of list item with opportunity to delete it
}