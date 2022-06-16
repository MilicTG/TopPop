package com.delminiusdevs.toppop.data.remote

import com.delminiusdevs.toppop.data.remote.dto.chart.DeezerDto
import com.delminiusdevs.toppop.data.remote.dto.detail.DeezerSongDetailDto
import retrofit2.http.Path
import retrofit2.http.GET
import retrofit2.http.Query

interface TopPopApi {

    @GET(value = "/chart")
    suspend fun getTopTenChart(): DeezerDto

    @GET(value = "/album")
    fun getAlbumDetails(
        @Query(value = "albumId") albumId: String
    ) : DeezerSongDetailDto
}