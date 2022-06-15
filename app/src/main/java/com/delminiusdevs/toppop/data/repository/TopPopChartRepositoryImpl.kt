package com.delminiusdevs.toppop.data.repository

import com.delminiusdevs.toppop.data.remote.TopPopApi
import com.delminiusdevs.toppop.domain.model.chart.DeezerData
import com.delminiusdevs.toppop.domain.repository.TopPopChartRepository
import com.delminiusdevs.toppop.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopPopChartRepositoryImpl @Inject constructor(
    private val topPopApi: TopPopApi
) : TopPopChartRepository {

    override suspend fun getTopTenChart(): Resource<DeezerData> {
        return try {
            Resource.Loading()
            
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}