package com.example.wifiqrcodes

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wifiqrcodes.databinding.FragmentWifiListBinding
import androidx.fragment.app.*

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

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        binding.fabAdd.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<AddNewItemFragment>(R.id.fragmentContainerView)
                addToBackStack(ADD_NEW_ITEM_TAG)
            }
        }

        binding.recyclerView.adapter = MyAdapter(viewModel.getItems())
    }

    // TODO: Save details about wifi QRs to a file and parse it (json) latter
    // TODO: Create fragment to write to a json file
    // TODO: Create fragment to observe details of list item with opportunity to delete it
}