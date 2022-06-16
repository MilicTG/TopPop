package com.delminiusdevs.toppop.presentation.fragments.top_ten

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.delminiusdevs.toppop.databinding.CardTopTenBinding
import com.delminiusdevs.toppop.domain.model.chart.DeezerData

class TopTenAdapter : RecyclerView.Adapter<TopTenAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<DeezerData>() {
        override fun areItemsTheSame(oldItem: DeezerData, newItem: DeezerData): Boolean {
            return oldItem.songName == newItem.songName
        }

        override fun areContentsTheSame(oldItem: DeezerData, newItem: DeezerData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var onItemClickListener: ((DeezerData) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardTopTenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chartItem: DeezerData = differ.currentList[position]
        holder.bind(chartItem)

        onItemClickListener?.let {
            it(chartItem)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size


    inner class ViewHolder(private val binding: CardTopTenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(deezerData: DeezerData) {
            binding.tvChartPosition.text = deezerData.songListNumber.toString()
            binding.tvChartSongName.text = deezerData.songName
            binding.tvChartArtistName.text = deezerData.artistName
            binding.tvChartSongDuration.text = deezerData.songDuration.toString()
        }
    }

}