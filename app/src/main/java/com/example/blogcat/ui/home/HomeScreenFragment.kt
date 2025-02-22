package com.example.blogcat.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.blogcat.R
import com.example.blogcat.core.Resource
import com.example.blogcat.data.remote.home.HomeScreenDataSource
import com.example.blogcat.databinding.FragmentHomeScreenBinding
import com.example.blogcat.domain.home.HomeScreenRepoImpl
import com.example.blogcat.presentation.home.HomeScreenViewModel
import com.example.blogcat.presentation.home.HomeScreenViewModelFactory
import com.example.blogcat.ui.home.adapter.HomeScreenAdapter


class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding
    private val viewModel by viewModels<HomeScreenViewModel>() {
        HomeScreenViewModelFactory(HomeScreenRepoImpl(HomeScreenDataSource()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)
        viewModel.fetchLatestPosts().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvHome.adapter = HomeScreenAdapter(result.data)
                }

                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Ocurrio un error", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}
