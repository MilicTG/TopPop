package com.delminiusdevs.toppop.domain.repository

import com.delminiusdevs.toppop.domain.model.chart.DeezerData
import com.delminiusdevs.toppop.util.Resource

interface TopPopChartRepository {

    suspend fun getTopTenChart(): Resource<DeezerData>
}