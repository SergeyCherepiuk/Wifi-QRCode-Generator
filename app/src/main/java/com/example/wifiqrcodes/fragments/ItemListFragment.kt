package com.example.wifiqrcodes.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.*
import com.example.wifiqrcodes.*
import com.example.wifiqrcodes.activities.AuthActivity
import com.example.wifiqrcodes.adapters.ViewPagerAdapter
import com.example.wifiqrcodes.databinding.FragmentItemListBinding
import com.example.wifiqrcodes.viewmodels.ItemsViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth


class ItemListFragment : Fragment(R.layout.fragment_item_list) {
    private lateinit var binding: FragmentItemListBinding
    private val viewModel: ItemsViewModel by activityViewModels()
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemListBinding.bind(view)

        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        binding.btnLogOut.setOnClickListener {
            auth.signOut()
            googleSignInClient.signOut()
            startActivity(Intent(requireActivity(), AuthActivity::class.java))
        }

        binding.viewPager.adapter = ViewPagerAdapter(
            fragment = this,
            viewModel = viewModel,
        )

        viewModel.adapter = binding.viewPager.adapter as ViewPagerAdapter

        // TODO: Set different icon to last page (add new item page)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->

        }.attach()
    }
}