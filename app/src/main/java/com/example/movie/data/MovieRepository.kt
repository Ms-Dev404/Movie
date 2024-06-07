package com.example.movie.data

import com.example.movie.network.ApiClient
import com.example.movie.network.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class MovieRepository(private val apiClient: ApiClient) {

 fun getMovies() = flow {

    try {
        val response = apiClient.getMovieList()

        response.body()?.status = response.isSuccessful
        response.body()?.message = if(response.isSuccessful){
        response.message().ifBlank { "success" } }else{ response.message().ifBlank {"Something went wrong"} }
        emit(response.body()!!)

    }catch(e:Exception){
      Timber.tag("REP").e(e)
      emit(MovieResponse(false,"An error occurred!", mutableListOf()))

    }

 }.flowOn(Dispatchers.IO)
}