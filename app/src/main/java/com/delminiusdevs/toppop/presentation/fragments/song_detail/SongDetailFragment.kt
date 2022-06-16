package com.delminiusdevs.toppop.presentation.fragments.song_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.delminiusdevs.toppop.databinding.FragmentSongDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongDetailFragment : Fragment() {

    private val songDetailViewModel: SongDetailViewModel by viewModels()
    private val args: SongDetailFragmentArgs by navArgs()

    lateinit var binding: FragmentSongDetailBinding

    lateinit var artistName: String


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

        val albumId = args.albumId
        artistName = args.artistName
        getSongDetail(albumId = albumId)
        observeData()
        Log.d("ovde", albumId.toString())
    }

    private fun getSongDetail(albumId: Int) {
        songDetailViewModel.getSongDetail(albumId = albumId)
    }

    private fun observeData(){

    }
}