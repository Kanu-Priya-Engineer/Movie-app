package bike.rapido.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.movie_list)

        MovieRepository.getPopularMoviesList(
            onSuccess = {
                Log.e("TAG", "Movie List size : ${it.size}")
                recyclerView.adapter = MovieAdapter(it)
            },
            onError = {
                Log.e("TAG", "Some error occured")
            }
        )

    }
}