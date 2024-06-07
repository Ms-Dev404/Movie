package com.example.movie.network

import com.example.movie.network.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
  @GET(MOVIE_LIST_API)
  suspend fun getMovieList():Response<MovieResponse>
}