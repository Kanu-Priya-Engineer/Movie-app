package bike.rapido.movieapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey : String = "66dda80468d72616f665696c142245bd",
    ) : Call<MovieResponse>
}