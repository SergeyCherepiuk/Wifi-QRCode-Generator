package com.example.wifiqrcodes.adapters

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wifiqrcodes.fragments.AddItemFragment
import com.example.wifiqrcodes.fragments.ItemFragment
import com.example.wifiqrcodes.viewmodels.ItemsViewModel

@RequiresApi(Build.VERSION_CODES.R)
class ViewPagerAdapter(
    fragment: Fragment,
    private var viewModel: ItemsViewModel,
) : FragmentStateAdapter(fragment) {
    private val fragmentIds = (viewModel.itemList.map { item ->
        ItemFragment(item).hashCode().toLong()
    } + AddItemFragment().hashCode().toLong()).toMutableList()

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            in viewModel.itemList.indices -> {
                ItemFragment(viewModel.itemList[position])
            }
            else -> {
                AddItemFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return viewModel.itemList.size + 1 // Items count + add new item page fragment
    }

    override fun getItemId(position: Int): Long {
        return fragmentIds[position]
    }

    override fun containsItem(itemId: Long): Boolean {
        return fragmentIds.contains(itemId)
    }

    fun removeItem(position: Int) {
        fragmentIds.removeAt(position)
        notifyItemRangeChanged(position, viewModel.itemList.size + 1)
        notifyItemRemoved(position)
    }
}