package com.delminiusdevs.toppop.presentation.fragments.top_ten

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.delminiusdevs.toppop.databinding.FragmentTopTenBinding
import com.delminiusdevs.toppop.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopTenFragment : Fragment() {

    private val topTenViewModel: TopTenViewModel by viewModels()

    lateinit var topTenAdapter: TopTenAdapter
    lateinit var binding: FragmentTopTenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopTenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
        onRefreshListener()
    }


    private fun setupRecyclerView() {
        topTenAdapter = TopTenAdapter()
        binding.rvTopTenChart.apply {
            adapter = topTenAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun observeData() {
        topTenViewModel.topTenChartList.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    hideErrorText()
                    response.data?.let {
                        topTenAdapter.differ.submitList(it.deezerData)
                    }
                    binding.topTenSwipeToRefresh.isRefreshing = false
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { showErrorText(text = it) }
                }
                is Resource.Loading -> {
                    showProgressBar()
                    showErrorText()
                }
                else -> Unit
            }
        })
    }

    private fun onRefreshListener() {
        binding.topTenSwipeToRefresh.setOnRefreshListener {
            topTenAdapter.differ.submitList(emptyList())
            topTenViewModel.getTopTenChart()
        }
    }

    private fun hideProgressBar() {
        binding.pbChartProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.pbChartProgressBar.visibility = View.VISIBLE
    }

    private fun hideErrorText() {
        binding.tvTopChartError.visibility = View.INVISIBLE
    }

    private fun showErrorText(text: String = "Loading...") {
        binding.tvTopChartError.visibility = View.VISIBLE
        binding.tvTopChartError.text = text
    }
}