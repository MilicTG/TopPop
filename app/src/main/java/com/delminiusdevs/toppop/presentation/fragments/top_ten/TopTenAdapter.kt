package com.delminiusdevs.toppop.presentation.fragments.top_ten

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.delminiusdevs.toppop.databinding.CardTopTenBinding
import com.delminiusdevs.toppop.domain.model.chart.DeezerData
import com.delminiusdevs.toppop.util.secondsFormatter

class TopTenAdapter(private val onClick:(DeezerData) -> Unit) : RecyclerView.Adapter<TopTenAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<DeezerData>() {
        override fun areItemsTheSame(oldItem: DeezerData, newItem: DeezerData): Boolean {
            return oldItem.songName == newItem.songName
        }

        override fun areContentsTheSame(oldItem: DeezerData, newItem: DeezerData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardTopTenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chartItem: DeezerData = differ.currentList[position]
        holder.bind(chartItem, onClick)
    }

    override fun getItemCount(): Int = differ.currentList.size


    inner class ViewHolder(private val binding: CardTopTenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(deezerData: DeezerData, onClick: (DeezerData) -> Unit) {
            binding.tvChartPosition.text = deezerData.songListNumber.toString()
            binding.tvChartSongName.text = deezerData.songName
            binding.tvChartArtistName.text = deezerData.artistName
            binding.tvChartSongDuration.text = secondsFormatter(deezerData.songDuration)
            binding.root.setOnClickListener {
                onClick(deezerData)
            }
        }
    }

}