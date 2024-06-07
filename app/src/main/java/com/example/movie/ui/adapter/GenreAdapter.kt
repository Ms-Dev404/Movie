package com.example.movie.ui.adapter

import android.graphics.Color

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.data.model.Genre
import com.example.movie.data.model.Movie
import com.example.movie.databinding.GenreRowBinding
import java.util.Random


class GenreAdapter(val itemClick:(movie:Movie)->Unit):RecyclerView.Adapter<GenreAdapter.VH>() {
    private val viewPool = RecyclerView.RecycledViewPool()

    inner class VH(private val rowBinding:GenreRowBinding):RecyclerView.ViewHolder(rowBinding.root){

        fun bind(genre: Genre) = with(rowBinding){
            tvGnre.setBackgroundColor(randomColor(adapterPosition))
            tvGnre.text = genre.genre
            val movieAdapter  = MovieAdapter(itemClick)
            rvMovie.apply {

                setRecycledViewPool(viewPool)
                layoutManager = LinearLayoutManager(root.context,LinearLayoutManager.HORIZONTAL,false)
                adapter = movieAdapter

            }
            movieAdapter.dataSet.submitList(genre.movieList)

        }
    }

    fun randomColor(position: Int): Int {

        if(position%2 == 0){

          return Color.parseColor("#C40233")
        }else if(position%2 == 1){
            return Color.parseColor("#2f6db4")
        }else{

           return Color.parseColor("#24562b")
        }


    }

val dataSet = AsyncListDiffer(this,Diffutil)
private object Diffutil:DiffUtil.ItemCallback<Genre>(){

    override fun areItemsTheSame(oldItem: Genre, newItem: Genre) = oldItem ==newItem

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre)= oldItem ==newItem

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

      val row = GenreRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
      return VH(row)
    }

    override fun getItemCount() = dataSet.currentList.size

    override fun onBindViewHolder(holder: VH, position: Int) {

        val item = dataSet.currentList[position]
        holder.bind(item)

    }
}