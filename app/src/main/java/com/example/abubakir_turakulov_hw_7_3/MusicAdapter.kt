package com.example.abubakir_turakulov_hw_7_3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abubakir_turakulov_hw_7_3.databinding.ItemMusicBinding

class MusicAdapter(private val songs: List<Song>, private val onItemClick: (Song) -> Unit) :
    RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemMusicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song, onItemClick: (Song) -> Unit) {
            binding.tvSongNumber.text = song.number.toString()
            binding.tvSongName.text = song.name
            binding.tvSongArtist.text = song.artist
            binding.tvSongDuration.text = song.duration

            binding.root.setOnClickListener {
                onItemClick(song)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]
        holder.bind(song, onItemClick)
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}