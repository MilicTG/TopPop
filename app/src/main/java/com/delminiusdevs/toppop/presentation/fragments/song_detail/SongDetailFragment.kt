package com.delminiusdevs.toppop.presentation.fragments.song_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.delminiusdevs.toppop.databinding.FragmentSongDetailBinding
import com.delminiusdevs.toppop.domain.model.detail.DeezerSongDetail
import com.delminiusdevs.toppop.util.Resource
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongDetailFragment : Fragment() {

    private val songDetailViewModel: SongDetailViewModel by viewModels()
    private val args: SongDetailFragmentArgs by navArgs()

    lateinit var binding: FragmentSongDetailBinding

    lateinit var songName: String

    lateinit var songListAdapter: ArrayAdapter<*>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideUi()
        val albumId = args.albumId
        songName = args.songName
        getSongDetail(albumId = albumId)
        observeData()
        Log.d("ovde", albumId.toString())
    }

    private fun getSongDetail(albumId: Int) {
        songDetailViewModel.getSongDetail(albumId = albumId)
    }

    private fun observeData() {
        songDetailViewModel.songDetail.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    hideErrorText()
                    response.data?.let {
                        updateUi(it)
                    }
                    showUi()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    hideUi()
                    response.message?.let { showErrorText(text = it) }
                }
                is Resource.Loading -> {
                    showProgressBar()
                    showErrorText()
                    hideUi()
                }
                else -> Unit
            }
        }
    }

    private fun updateUi(deezerSongDetail: DeezerSongDetail) {

        Picasso.get()
            .load(deezerSongDetail.coverImage)
            .resize(350, 350)
            .centerCrop()
            .into(binding.imSongDetail)

        binding.tvDetailSongName.text = songName
        binding.tvDetailArtistName.text = deezerSongDetail.artistName
        binding.tvDetailAlbumName.text = deezerSongDetail.albumName

        songListAdapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_list_item_1,
            deezerSongDetail.listOfSongs.map {
                it.songTitle
            })
        binding.tvDetailSongList.adapter = songListAdapter
    }

    private fun hideUi() {
        binding.imSongDetail.visibility = View.INVISIBLE
        binding.tvDetailAlbumName.visibility = View.INVISIBLE
        binding.tvDetailSongName.visibility = View.INVISIBLE
        binding.tvDetailArtistName.visibility = View.INVISIBLE
        binding.detailSongListContainer.visibility = View.INVISIBLE
        binding.tvDetailSongList.visibility = View.INVISIBLE
    }

    private fun showUi() {
        binding.imSongDetail.visibility = View.VISIBLE
        binding.tvDetailAlbumName.visibility = View.VISIBLE
        binding.tvDetailSongName.visibility = View.VISIBLE
        binding.tvDetailArtistName.visibility = View.VISIBLE
        binding.detailSongListContainer.visibility = View.VISIBLE
        binding.tvDetailSongList.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.pbSongDetailProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.pbSongDetailProgressBar.visibility = View.VISIBLE
    }

    private fun hideErrorText() {
        binding.tvSongDetailError.visibility = View.INVISIBLE
    }

    private fun showErrorText(text: String = "Loading...") {
        binding.tvSongDetailError.visibility = View.VISIBLE
        binding.tvSongDetailError.text = text
    }
}