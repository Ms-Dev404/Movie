package com.example.movie.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.ui.viewmodel.MovieViewModel
import com.example.movie.utils.instance

@Suppress("UNCHECKED_CAST")
class ViewModelFactory:ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MovieViewModel::class.java)){

           return MovieViewModel(instance!!.repository) as T
        }

        throw IllegalAccessException()
    }
}