package com.delminiusdevs.toppop.presentation.fragments.top_ten

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.delminiusdevs.toppop.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopTenFragment : Fragment(R.layout.fragment_top_ten) {

   private val topTenViewModel : TopTenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ovde", topTenViewModel.topTenChartList.value?.data?.deezerData.toString())
    }

}