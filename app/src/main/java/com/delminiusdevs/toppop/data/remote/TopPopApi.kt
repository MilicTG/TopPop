package com.delminiusdevs.toppop.data.remote

import com.delminiusdevs.toppop.data.remote.dto.chart.DeezerDto
import retrofit2.http.Path

import retrofit2.http.GET

interface TopPopApi {

    @GET(value = "/chart")
    suspend fun getTopTenChart(): DeezerDto

    @GET(value = "/album/{albumId}")
    fun getAlbumDetails(
        @Path(value = "albumId") albumId: String
    )
}