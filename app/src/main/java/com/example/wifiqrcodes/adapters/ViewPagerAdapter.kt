package com.example.wifiqrcodes.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wifiqrcodes.database.Item
import com.example.wifiqrcodes.fragments.AddItemFragment
import com.example.wifiqrcodes.fragments.ItemFragment


class ViewPagerAdapter(
    fragment: Fragment,
    var itemList: MutableList<Item>,
) : FragmentStateAdapter(fragment) {
    private val fragmentIds = (itemList.map { item ->
        ItemFragment(item).hashCode().toLong()
    } + AddItemFragment().hashCode().toLong()).toMutableList()

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            in itemList.indices -> {
                ItemFragment(itemList[position])
            }
            else -> {
                AddItemFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size + 1 // Items count + add new item page fragment
    }

    override fun getItemId(position: Int): Long {
        return fragmentIds[position]
    }

    override fun containsItem(itemId: Long): Boolean {
        return fragmentIds.contains(itemId)
    }

    fun removeItem(position: Int): Item {
        val item: Item = itemList.removeAt(position)
        fragmentIds.removeAt(position)
        notifyItemRangeChanged(position, itemList.size + 1)
        notifyItemRemoved(position)
        return item
    }
}