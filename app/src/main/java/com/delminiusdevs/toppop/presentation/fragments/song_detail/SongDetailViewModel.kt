package com.delminiusdevs.toppop.presentation.fragments.song_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delminiusdevs.toppop.domain.model.detail.DeezerSongDetail
import com.delminiusdevs.toppop.domain.repository.TopPopChartRepository
import com.delminiusdevs.toppop.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongDetailViewModel @Inject constructor(
    private val topPopChartRepository: TopPopChartRepository
) : ViewModel() {

    private val _songDetail = MutableLiveData<Resource<DeezerSongDetail>>()
    val songDetail: LiveData<Resource<DeezerSongDetail>?> = _songDetail
    
    fun getSongDetail(albumId: Int) {
        viewModelScope.launch {
            _songDetail.value = topPopChartRepository.getSongDetail(albumId = albumId)
        }
    }
}