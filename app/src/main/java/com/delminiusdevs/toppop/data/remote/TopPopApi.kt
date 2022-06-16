package com.delminiusdevs.toppop.data.remote

import com.delminiusdevs.toppop.data.remote.dto.chart.DeezerDto
import com.delminiusdevs.toppop.data.remote.dto.detail.DeezerSongDetailDto
import retrofit2.http.Path
import retrofit2.http.GET

interface TopPopApi {

    @GET(value = "/chart")
    suspend fun getTopTenChart(): DeezerDto

    @GET(value = "/album/{album_id}")
    suspend fun getAlbumDetails(
        @Path(value = "album_id") albumId: Int
    ) : DeezerSongDetailDto
}