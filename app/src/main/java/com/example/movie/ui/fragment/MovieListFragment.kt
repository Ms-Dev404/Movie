package com.example.movie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.databinding.FragmentMovieListBinding
import com.example.movie.ui.adapter.GenreAdapter
import com.example.movie.ui.viewmodel.MovieViewModel
import com.example.movie.ui.viewmodel.factory.ViewModelFactory
import com.example.movie.utils.hideProgress
import com.example.movie.utils.showProgress
import com.example.movie.utils.showToast

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val viewModel by activityViewModels<MovieViewModel> { ViewModelFactory() }
    private val binding get() = _binding!!

    private val genreAdapter = GenreAdapter{

        viewModel._movieDetails.postValue(it)
        findNavController().navigate(R.id.action_MovieListFragment_to_MovieDetailsFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {

            rvGenre.layoutManager = LinearLayoutManager(requireContext())
            rvGenre.adapter = genreAdapter


        }

        if(genreAdapter.dataSet.currentList.isEmpty()) {
            requireContext().showProgress("fetching movies...")
        }
        viewModel.moviesList().observe(viewLifecycleOwner){result->


            if(result.status){

               if(result.genreList.isNotEmpty()){

                   genreAdapter.dataSet.submitList(result.genreList)
               }else{

                   requireContext().showToast("No data found!")
               }
            }else{

                requireContext().showToast(result.message)
            }

            requireContext().hideProgress()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}