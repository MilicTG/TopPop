package com.delminiusdevs.toppop.domain.repository

import com.delminiusdevs.toppop.domain.model.chart.DeezerChart
import com.delminiusdevs.toppop.domain.model.detail.DeezerSongDetail
import com.delminiusdevs.toppop.util.Resource

interface TopPopChartRepository {
    suspend fun getTopTenChart(): Resource<DeezerChart>
    suspend fun getSongDetail(albumId: Int): Resource<DeezerSongDetail>
}