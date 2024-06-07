package com.example.movie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.movie.data.MovieRepository
import com.example.movie.data.model.Movie

class MovieViewModel(private val repository: MovieRepository):ViewModel() {

    val _movieDetails = MutableLiveData<Movie>()
    val movieDetails get() = _movieDetails

    fun moviesList() = repository.getMovies().asLiveData()
}

