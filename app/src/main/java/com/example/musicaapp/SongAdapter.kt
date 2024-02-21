import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicaapp.R
import com.example.musicaapp.Song

class SongAdapter(private val songs: List<Song>, private val mainActivity: MainActivity) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewSongTitle: TextView = itemView.findViewById(R.id.textView_song_title)
        val buttonPlay: Button = itemView.findViewById(R.id.button_play)
        val buttonPause: Button = itemView.findViewById(R.id.button_pause)
        val buttonStop: Button = itemView.findViewById(R.id.button_stop)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]
        holder.textViewSongTitle.text = song.name

        holder.buttonPlay.setOnClickListener {
            mainActivity.startSong(song.resourceId)
        }

        holder.buttonPause.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }

        holder.buttonStop.setOnClickListener {
            mediaPlayer.stop()
        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}
