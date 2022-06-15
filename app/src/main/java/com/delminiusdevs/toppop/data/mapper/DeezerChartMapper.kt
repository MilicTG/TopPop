package com.delminiusdevs.toppop.data.mapper

import com.delminiusdevs.toppop.data.remote.dto.chart.DeezerDto
import com.delminiusdevs.toppop.domain.model.chart.DeezerChart
import com.delminiusdevs.toppop.domain.model.chart.DeezerData

object DeezerChartMapper {
    fun from(from: DeezerDto) = DeezerChart(
        deezerData = from.tracks.data.map {
            DeezerData(
                songListNumber = it.position,
                artistName = it.artist.name,
                songName = it.title,
                songDuration = it.duration,
                albumId = it.album.id
            )
        }
    )
}