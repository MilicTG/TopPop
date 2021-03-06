package com.delminiusdevs.toppop.presentation.fragments.top_ten

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delminiusdevs.toppop.domain.model.chart.DeezerChart
import com.delminiusdevs.toppop.domain.model.chart.DeezerData
import com.delminiusdevs.toppop.domain.repository.TopPopChartRepository
import com.delminiusdevs.toppop.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopTenViewModel @Inject constructor(
    private val topPopChartRepository: TopPopChartRepository
) : ViewModel() {

    private val _topTenChartList = MutableLiveData<Resource<DeezerChart>>()
    val topTenChartList: LiveData<Resource<DeezerChart>?> = _topTenChartList

    var sortedChartList: List<DeezerData>? = emptyList()

    init {
        getTopTenChart()
    }

    fun getTopTenChart() {
        viewModelScope.launch {
            _topTenChartList.value = topPopChartRepository.getTopTenChart()
        }
    }

    fun sortDeezerChartAsc() {
        sortedChartList = _topTenChartList.value?.data?.deezerData?.sortedBy {
            it.songDuration
        }
    }

    fun sortDeezerChartDesc() {
        sortedChartList = _topTenChartList.value?.data?.deezerData?.sortedByDescending {
            it.songDuration
        }
    }

    fun sortDeezerChartNormal() {
        sortedChartList = emptyList()
    }
}