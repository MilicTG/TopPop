package com.delminiusdevs.toppop.presentation.fragments.top_ten

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delminiusdevs.toppop.data.remote.dto.chart.DeezerDto
import com.delminiusdevs.toppop.domain.model.chart.DeezerChart
import com.delminiusdevs.toppop.domain.repository.TopPopChartRepository
import com.delminiusdevs.toppop.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopTenViewModel @Inject constructor(
    private val topPopChartRepository: TopPopChartRepository
) : ViewModel() {

//    private val _mediaItems = MutableLiveData<Resource<List<Song>>>()
//    val mediaItems: LiveData<Resource<List<Song>>> = _mediaItems

    private val _topTenChartList = MutableLiveData<Resource<DeezerChart>?>()
    val topTenChartList: LiveData<Resource<DeezerChart>?> = _topTenChartList


    init {
        viewModelScope.launch {
            _topTenChartList.value = topPopChartRepository.getTopTenChart()
            delay(2000)
            Log.d("ovde", topTenChartList.value?.data?.deezerData.toString())
        }
    }
}