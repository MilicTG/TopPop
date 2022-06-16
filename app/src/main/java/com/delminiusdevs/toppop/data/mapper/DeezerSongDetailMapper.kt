package com.delminiusdevs.toppop.data.mapper

import com.delminiusdevs.toppop.data.remote.dto.detail.DeezerSongDetailDto
import com.delminiusdevs.toppop.domain.model.detail.AlbumSongList
import com.delminiusdevs.toppop.domain.model.detail.DeezerSongDetail

object DeezerSongDetailMapper {
    fun from(from: DeezerSongDetailDto) = DeezerSongDetail(
        id = from.id,
        albumName = from.title,
        artistName = from.artist.name,
        coverImage = from.cover_medium,
        listOfSongs = from.tracks.data.map {
            AlbumSongList(
                songTitle = it.title
            )
        }
    )
}