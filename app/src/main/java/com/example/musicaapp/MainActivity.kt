import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicaapp.R
import com.example.musicaapp.Song

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer()

        val songList = listOf(
            Song("Beat It - Michael Jackson", R.raw.michael_jackson_beat_it),
            Song("Pitbull - Guantanamera", R.raw.pitbull_guantanamera),
            Song("Rick Astley - Never Gonna Give You Up", R.raw.rick_astley_never_gonna_give_you_up)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = SongAdapter(songList, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release() // Liberar recursos cuando se destruye la actividad
    }

    fun startSong(songId: Int) {
        mediaPlayer.apply {
            reset()
            setDataSource(resources.openRawResourceFd(songId))
            prepare()
            start()
        }
    }
}
