package com.delminiusdevs.toppop.presentation.fragments.top_ten

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.delminiusdevs.toppop.R
import com.delminiusdevs.toppop.databinding.FragmentTopTenBinding
import com.delminiusdevs.toppop.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopTenFragment : Fragment() {

    private val topTenViewModel: TopTenViewModel by viewModels()

    lateinit var topTenAdapter: TopTenAdapter
    lateinit var binding: FragmentTopTenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
        inflateToolbar()
        setupRecyclerView()
        observeData()
        onRefreshListener()
    }

    private fun inflateToolbar() {
        binding.topTenToolbar.inflateMenu(R.menu.top_pop_menu)
        setAppBarOnClickListener()
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

    private fun setAppBarOnClickListener() {
        binding.topTenToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.chartSortNormal -> {
                    topTenViewModel.sortDeezerChartNormal()
                    topTenAdapter.differ.submitList(topTenViewModel.topTenChartList.value?.data?.deezerData)
                    true
                }
                R.id.chartSortAsc -> {
                    topTenViewModel.sortDeezerChartAsc()
                    topTenAdapter.differ.submitList(topTenViewModel.sortedChartList?.toList())
                    true
                }
                R.id.chartSortDesc -> {
                    topTenViewModel.sortDeezerChartDesc()
                    topTenAdapter.differ.submitList(topTenViewModel.sortedChartList?.toList())
                    true
                }
                else -> false
            }
        }
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