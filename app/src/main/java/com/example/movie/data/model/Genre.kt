package com.example.movie.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Genre(@SerializedName("id") @Expose val id:Int,@SerializedName("genre") @Expose val genre:String,@SerializedName("movieslist") @Expose val movieList:MutableList<Movie>)
