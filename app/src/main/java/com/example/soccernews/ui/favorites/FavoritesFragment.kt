package com.example.soccernews.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccernews.NewsApplication
import com.example.soccernews.databinding.FragmentFavoritesBinding
import com.example.soccernews.domain.News
import com.example.soccernews.ui.adapter.NewsAdapter

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null

    private val binding get() = _binding!!
    private val newsViewModel : FavoritesViewModel by viewModels {
        FavoriteNewsViewModelFactory((activity?.application as NewsApplication).repository)
    }

    private val adapter : NewsAdapter by lazy {
        NewsAdapter{ news ->
            saveNews(news)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayoutManager()
        loadFavoriteNews()
    }


    private fun setLayoutManager() {
        binding.rvFavorites.layoutManager = LinearLayoutManager(context)
    }

    private fun loadFavoriteNews() {
        binding.rvFavorites.adapter = adapter
        newsViewModel.news.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun saveNews(news: News){
        newsViewModel.saveNews(news)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}