package com.example.abubakir_turakulov_hw_7_3

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.abubakir_turakulov_hw_7_3.databinding.FragmentMusicListBinding

class MusicListFragment : Fragment() {
    private lateinit var binding: FragmentMusicListBinding
    private lateinit var musicAdapter: MusicAdapter
    private var songs: MutableList<Song> = mutableListOf()
    private val permissionRequestCode = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicListBinding.inflate(inflater, container, false)
        val view = binding.root

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getSongsFromStorage()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), permissionRequestCode
            )
            binding.tvMusicFounded.visibility = View.VISIBLE
            binding.rvMusic.visibility = View.GONE
        }
        return view
    }

    private fun formatDuration(duration: Long): String {
        val minutes = (duration / 1000 / 60).toInt()
        val seconds = (duration / 1000 % 60).toInt()
        return String.format("%02d:%02d", minutes, seconds)
    }

    private fun getSongsFromStorage() {
        val resolver = requireActivity().contentResolver
        val musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val music = resolver.query(musicUri, null, null, null, null)

        var songNumber = 1

        if (music != null && music.moveToFirst()) {
            val titleColumn = music.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val artistColumn = music.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val durationColumn = music.getColumnIndex(MediaStore.Audio.Media.DURATION)

            do {
                val songName = music.getString(titleColumn)
                var songArtist = music.getString(artistColumn)
                if (songArtist == "<unknown>") {
                    songArtist = "Неизвестно"
                }
                val songDuration = music.getLong(durationColumn)
                val formattedDuration = formatDuration(songDuration)

                val song = Song(songNumber, songName, songArtist, formattedDuration)
                songs.add(song)
                songNumber++
            } while (music.moveToNext())
            music.close()
        }

        musicAdapter = MusicAdapter(songs) { selectedSong ->
            val intent = Intent(activity, PlayActivity::class.java)
            intent.putExtra("SONG_NAME", selectedSong.name)
            startActivity(intent)
        }
        binding.rvMusic.adapter = musicAdapter
    }
}