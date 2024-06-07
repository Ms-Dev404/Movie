package com.example.movie.network.model

import com.example.movie.data.model.Genre
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(var status:Boolean,var message:String,@SerializedName("homeData") @Expose val genreList:MutableList<Genre> )
