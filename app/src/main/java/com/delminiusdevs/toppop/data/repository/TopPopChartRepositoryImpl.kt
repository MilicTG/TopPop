package com.delminiusdevs.toppop.data.repository

import com.delminiusdevs.toppop.data.mapper.DeezerChartMapper
import com.delminiusdevs.toppop.data.mapper.DeezerSongDetailMapper
import com.delminiusdevs.toppop.data.remote.TopPopApi
import com.delminiusdevs.toppop.domain.model.chart.DeezerChart
import com.delminiusdevs.toppop.domain.model.detail.DeezerSongDetail
import com.delminiusdevs.toppop.domain.repository.TopPopChartRepository
import com.delminiusdevs.toppop.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopPopChartRepositoryImpl @Inject constructor(
    private val topPopApi: TopPopApi
) : TopPopChartRepository {

    override suspend fun getTopTenChart(): Resource<DeezerChart> {
        return try {
            val response = topPopApi.getTopTenChart()

            if (response.tracks.data.isEmpty()) {
                Resource.Loading()
            } else {
                Resource.Success(
                    data = DeezerChartMapper.from(response)
                )
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getSongDetail(albumId: Int): Resource<DeezerSongDetail> {
        return try {
            val response = topPopApi.getAlbumDetails(albumId = albumId)

            Resource.Success(
                data = DeezerSongDetailMapper.from(response)
            )

        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}