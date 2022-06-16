package com.delminiusdevs.toppop.domain.model.detail

data class DeezerSongDetail(
    val id: Int,
    val artistName: String,
    val albumName: String,
    val coverImage: String,
    val listOfSongs: List<AlbumSongList>
)
