package bike.rapido.movieapp

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository {
    private val api:Api
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }

    fun getPopularMoviesList(
        onSuccess: (movieList: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {
        api.getPopularMovies()
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            Log.e("Repository", "Movies: ${responseBody.movies}")
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            Log.e("Repository", "Failed to get response")
                            onError.invoke()
                        }
                    } else {
                        Log.e("Repository", "Resp : ${response}")
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }
            })
    }

}