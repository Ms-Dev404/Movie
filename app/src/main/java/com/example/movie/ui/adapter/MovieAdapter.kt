package com.example.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movie.data.model.Genre
import com.example.movie.data.model.Movie
import com.example.movie.databinding.MovieRowBinding

class MovieAdapter(private val itemClick:(movie:Movie)->Unit):RecyclerView.Adapter<MovieAdapter.MVH>() {
    inner class MVH(private val rowBinding: MovieRowBinding):RecyclerView.ViewHolder(rowBinding.root) {

        fun bind(movie: Movie) = with(rowBinding){

            txtTitle.apply {
                text = movie.title
                isSelected = true
            }
            val rating = movie.rating.toFloat()
            ratingBar.rating = if(rating>=5f){5f}else{rating/5f}
            ivBanner.load(movie.posterUrl)
            ivBanner.setOnClickListener {
                itemClick(movie)
            }
        }
    }

    val dataSet = AsyncListDiffer(this,Diffutil)
    private object Diffutil: DiffUtil.ItemCallback<Movie>(){

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem ==newItem

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie)= oldItem ==newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MVH {

        val row  = MovieRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MVH(row)
    }

    override fun getItemCount() = dataSet.currentList.size

    override fun onBindViewHolder(holder: MVH, position: Int) {

        val item = dataSet.currentList[position]
        holder.bind(item)
    }
}