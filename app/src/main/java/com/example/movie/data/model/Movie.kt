package com.example.movie.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(@SerializedName("title") @Expose val title:String,@SerializedName("desc") @Expose val desc:String,@SerializedName("posterurl") @Expose val posterUrl:String,@SerializedName("release") @Expose val release:String,@SerializedName("rating") @Expose val rating:String,@SerializedName("genre") @Expose val genreList:ArrayList<String>)
