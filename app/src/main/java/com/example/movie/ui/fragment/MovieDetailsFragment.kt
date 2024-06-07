package com.example.movie.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.movie.databinding.FragmentMovieDetailsBinding
import com.example.movie.ui.viewmodel.MovieViewModel
import com.example.movie.ui.viewmodel.factory.ViewModelFactory


class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val viewModel by activityViewModels<MovieViewModel> { ViewModelFactory() }


    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

         viewModel.movieDetails.observe(viewLifecycleOwner){details->

             details?.let {
               tvName.isSelected = true
               ivBanner.load(details.posterUrl)
               tvName.text = details.title
               tvRelease.text = "Released on :${details.release}"
               categoryTv.text = details.genreList.toString()
               tvDsc.text = details.desc
               val rate = details.rating.toFloat()
               ratingBar.rating = if(rate>=5f){rate}else{
                   5f/rate
               }
                 btnFind.setOnClickListener {

                     startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${details.title}")))
                 }
             }


         }



        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}