package com.example.movie.utils

import android.app.Application
import com.example.movie.data.MovieRepository
import com.example.movie.network.ApiClient
import com.example.movie.network.RetrofitProvider
import timber.log.Timber
import timber.log.Timber.Forest.plant


var instance:App? =null
class App:Application() {

    private val apiClient  by lazy {RetrofitProvider.buildInstance()?.create(ApiClient::class.java)!!}
    val repository by lazy { MovieRepository(apiClient) }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        plant(Timber.DebugTree())


    }
}

